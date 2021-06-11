package com.prokarma.employee.info;

import static org.mockito.Mockito.doNothing;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
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

import com.prokarama.beans.Response;
import com.prokarma.entity.EmployeeDetails;
import com.prokarma.entity.InsuranceCardDetails;
import com.prokarma.repositories.IEmployeeDetailsRepository;
import com.prokarma.repositories.IInsuranceCardDetailsRepository;
import com.prokarma.rest.controller.IEmployeeInformationController;
import com.prokarma.rest.service.IEmployeeDetailsService;
import com.prokarma.rest.service.IInsuranceCardDetailsService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class DeactiveEmployeeTests {

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
	@BeforeEach
	public void loadUpdateEmployeeRequest() {
		Mockito.when(employeeDetailsRepository.findById(Mockito.any())).thenReturn(Optional.of(new EmployeeDetails()));
	}
	@Test
	public void deactiveEmployeeDetailsTestNull() {
		ResponseEntity<Response<EmployeeDetails>> response=employeeInfromationController.deactiveEmployeeDetails(null);
		Assert.assertEquals(response.getBody().getResponseCode(), "211");
	}
	
	@Test
	public void deactiveEmployeeDetailsTestEmpty() {
		Mockito.when(employeeDetailsRepository.findById(Mockito.any())).thenReturn(Optional.ofNullable(null));
		ResponseEntity<Response<EmployeeDetails>> response=employeeInfromationController.deactiveEmployeeDetails("234");
		Assert.assertEquals(response.getBody().getResponseCode(), "2893");
	}
	
	@Test
	public void deactiveEmployeeDetailsTestInsuranceDetailsNotFound() {
		Mockito.when(insuranceCardDetailsRepository.findByEmployeeDetails(Mockito.any())).thenReturn(new ArrayList<>());
		Mockito.when(employeeDetailsRepository.save(Mockito.any())).thenReturn(new EmployeeDetails());
		ResponseEntity<Response<EmployeeDetails>> response=employeeInfromationController.deactiveEmployeeDetails("234");
		Assert.assertEquals(response.getBody().getResponseCode(), "0000");
	}
	
	@Test
	public void deactiveEmployeeDetailsTestInsuranceDetailsSuccess() {
		List<InsuranceCardDetails> insuranceCardDetails=new ArrayList<>();
		insuranceCardDetails.add(new InsuranceCardDetails());
		Mockito.when(insuranceCardDetailsRepository.findByEmployeeDetails(Mockito.any())).thenReturn(insuranceCardDetails);
		Mockito.when(employeeDetailsRepository.save(Mockito.any())).thenReturn(new EmployeeDetails());
		Mockito.when(insuranceCardDetailsRepository.save(Mockito.any())).thenReturn(new InsuranceCardDetails());
		doNothing().when(jmsTemplate).convertAndSend(Mockito.any());
		ResponseEntity<Response<EmployeeDetails>> response=employeeInfromationController.deactiveEmployeeDetails("234");
		Assert.assertEquals(response.getBody().getResponseCode(), "0000");
	}

}
