package com.prokarma.employee.info;


import static org.mockito.Mockito.doNothing;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.hibernate.HibernateException;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import com.prokarama.beans.CreateEmployeeRequest;
import com.prokarama.beans.EmployeeAdditionalDetails;
import com.prokarama.beans.Response;
import com.prokarma.entity.EmployeeDetails;
import com.prokarma.entity.InsuranceCardDetails;
import com.prokarma.repositories.IEmployeeDetailsRepository;
import com.prokarma.repositories.IInsuranceCardDetailsRepository;
import com.prokarma.rest.controller.IEmployeeInformationController;
import com.prokarma.rest.controller.implementation.EmployeeInformationController;
import com.prokarma.rest.service.IEmployeeDetailsService;
import com.prokarma.rest.service.IInsuranceCardDetailsService;
import com.prokarma.rest.service.Implementation.EmployeeDetailsService;
import com.prokarma.rest.service.Implementation.InsuranceCardDetailsService;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class EmployeeApplicationTests {
	@Autowired
	private IEmployeeInformationController employeeInfromationController;
	@Autowired
	private IEmployeeDetailsService employeeDetailsService;
	@MockBean
	private IEmployeeDetailsRepository employeeDetailsRepository;
	@MockBean
	private IInsuranceCardDetailsRepository insuranceCardDetailsRepository;
	@MockBean
	private JmsTemplate jmsTemplate;
	@Autowired
	private IInsuranceCardDetailsService insuranceCardDetailsService;
	
	private CreateEmployeeRequest createEmployeeRequest;
 
	@BeforeEach
	public void loadingBefore() {
		createEmployeeRequest=new CreateEmployeeRequest();
		addingRequestParameters(createEmployeeRequest);
	}
	@Test
	void createEmployeeDetailsTestNull() {
		CreateEmployeeRequest createEmployeeRequest=null;
		//Mockito.when(IEmployeeDetailsService.class).then(employeeDetailsService);
		ResponseEntity<Response<EmployeeDetails>> response=employeeInfromationController.createEmployeeDetails(createEmployeeRequest);
		Assert.assertEquals(response.getBody().getResponseCode(), "211");
	}
	
	@Test
	void createEmployeeDetailsTestInvalidDate() {
		createEmployeeRequest.setDob("22-04-1997");
		ResponseEntity<Response<EmployeeDetails>> response=employeeInfromationController.createEmployeeDetails(createEmployeeRequest);
		Assert.assertEquals(response.getBody().getResponseCode(), "2892");
	}

	@Test
	void createEmployeeDetailsTestHibernateException() {
		Mockito.when(employeeDetailsRepository.save(Mockito.any())).thenThrow(new HibernateException("hibernate exception"));
		ResponseEntity<Response<EmployeeDetails>> response=employeeInfromationController.createEmployeeDetails(createEmployeeRequest);
		Assert.assertEquals(response.getBody().getResponseCode(), "2891");
	}
	
	@Test
	void createEmployeeDetailsTestSuccess() {
		Mockito.when(employeeDetailsRepository.save(Mockito.any())).thenReturn(new EmployeeDetails());
		doNothing().when(jmsTemplate).convertAndSend(Mockito.any());
		ResponseEntity<Response<EmployeeDetails>> response=employeeInfromationController.createEmployeeDetails(createEmployeeRequest);
		Assert.assertEquals(response.getBody().getResponseCode(), "0000");
	}
	@Test
	void getEmployeeDetailsTestNullEmployeeId() {
		ResponseEntity<Response<EmployeeDetails>> response=employeeInfromationController.getEmployeeDetails(null);
		Assert.assertEquals(response.getBody().getResponseCode(), "211");
	}
	@Test
	void getEmployeeDetailsTestEmployeeDetailsEmpty() {
		Mockito.when(employeeDetailsRepository.findById(Mockito.any())).thenReturn(Optional.ofNullable(null));
		ResponseEntity<Response<EmployeeDetails>> response=employeeInfromationController.getEmployeeDetails("298");
		Assert.assertEquals(response.getBody().getResponseCode(), "2893");
	}
	
	@Test
	void getEmployeeDetailsTestEmployeeDetailsSuccess() {
		Mockito.when(employeeDetailsRepository.findById(Mockito.any())).thenReturn(Optional.of(new EmployeeDetails()));
		ResponseEntity<Response<EmployeeDetails>> response=employeeInfromationController.getEmployeeDetails("298");
		Assert.assertEquals(response.getBody().getResponseCode(), "0000");
	}
	@Test
	void getAllEmployeeDetailsTestEmpty() {
		List<EmployeeDetails> empList=new ArrayList<>();
		Mockito.when(employeeDetailsRepository.findAll()).thenReturn(empList);
		ResponseEntity<Response<List<EmployeeDetails>>> response=employeeInfromationController.getAllEmployeeDetails();
		Assert.assertEquals(response.getBody().getResponseCode(), "2893");
	}
	@Test
	void getAllEmployeeDetailsTestSuccess() {
		List<EmployeeDetails> empList=new ArrayList<>();
		empList.add(new EmployeeDetails());
		Mockito.when(employeeDetailsRepository.findAll()).thenReturn(empList);
		ResponseEntity<Response<List<EmployeeDetails>>> response=employeeInfromationController.getAllEmployeeDetails();
		Assert.assertEquals(response.getBody().getResponseCode(), "0000");
	}
	private void addingRequestParameters(CreateEmployeeRequest createEmployeeRequest) {
		createEmployeeRequest.setDesignation("software");
		createEmployeeRequest.setDob("22/04/1997");
		EmployeeAdditionalDetails employeeAdditionalDetails=new EmployeeAdditionalDetails();
		employeeAdditionalDetails.setAddress("warangal");
		employeeAdditionalDetails.setBloodGroup("A+");
		createEmployeeRequest.setAdditionalDetails(employeeAdditionalDetails);

	}
}
