package com.prokarma.employee.info;

import static org.mockito.Mockito.doNothing;

import java.util.Optional;

import org.hibernate.HibernateException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.prokarama.beans.CreateEmployeeRequest;
import com.prokarama.beans.Response;
import com.prokarama.beans.UpdateEmployeeRequest;
import com.prokarma.entity.EmployeeDetails;
import com.prokarma.repositories.IEmployeeDetailsRepository;
import com.prokarma.repositories.IInsuranceCardDetailsRepository;
import com.prokarma.rest.controller.IEmployeeInformationController;
import com.prokarma.rest.service.IEmployeeDetailsService;
import com.prokarma.rest.service.IInsuranceCardDetailsService;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class UpdateEmployeeTests {
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
	private UpdateEmployeeRequest updateEmployeeRequest;
	
	@BeforeEach
	void loadUpdateEmployeeRequest() {
		updateEmployeeRequest=new UpdateEmployeeRequest();
		addingRequestParameters(updateEmployeeRequest);
		Mockito.when(employeeDetailsRepository.findById(Mockito.any())).thenReturn(Optional.of(new EmployeeDetails()));
	}
	@Test
	void UpdateEmployeeDetailsTestNull() {
		UpdateEmployeeRequest updateEmployeeRequest=null;
		//Mockito.when(IEmployeeDetailsService.class).then(employeeDetailsService);
		ResponseEntity<Response<EmployeeDetails>> response=employeeInfromationController.updateEmployeeDetails(updateEmployeeRequest);
		Assert.assertEquals(response.getBody().getResponseCode(), "211");
	}
	@Test
	void UpdateEmployeeDetailsTestEmployeeIdNotFound() {
		Mockito.when(employeeDetailsRepository.findById(Mockito.any())).thenReturn(Optional.ofNullable(null));
		ResponseEntity<Response<EmployeeDetails>> response=employeeInfromationController.updateEmployeeDetails(updateEmployeeRequest);
		Assert.assertEquals(response.getBody().getResponseCode(), "2893");
	}
	@Test
	void updateEmployeeDetailsTestInvalidDate() {
		updateEmployeeRequest.setDob("22-04-1997");
		ResponseEntity<Response<EmployeeDetails>> response=employeeInfromationController.updateEmployeeDetails(updateEmployeeRequest);
		Assert.assertEquals(response.getBody().getResponseCode(), "2892");
	}

	@Test
	void updateEmployeeDetailsTestHibernateException() {
		Mockito.when(employeeDetailsRepository.save(Mockito.any())).thenThrow(new HibernateException("hibernate exception"));
		ResponseEntity<Response<EmployeeDetails>> response=employeeInfromationController.updateEmployeeDetails(updateEmployeeRequest);
		Assert.assertEquals(response.getBody().getResponseCode(), "2891");
	}
	
	@Test
	void updateEmployeeDetailsTestSuccess() {
		Mockito.when(employeeDetailsRepository.save(Mockito.any())).thenReturn(new EmployeeDetails());
		ResponseEntity<Response<EmployeeDetails>> response=employeeInfromationController.updateEmployeeDetails(updateEmployeeRequest);
		Assert.assertEquals(response.getBody().getResponseCode(), "0000");
	}
	private void addingRequestParameters(UpdateEmployeeRequest updateEmployeeRequest) {
		updateEmployeeRequest.setDesignation("software");
		updateEmployeeRequest.setDob("22/04/1997");

	}
}
