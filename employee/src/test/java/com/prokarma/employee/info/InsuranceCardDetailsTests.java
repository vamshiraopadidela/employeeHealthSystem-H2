package com.prokarma.employee.info;

import static org.mockito.Mockito.doNothing;

import java.util.Optional;

import org.hibernate.HibernateException;
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

import com.prokarama.beans.CreateInsuranceCardDetailsRequest;
import com.prokarama.beans.EmployeeAdditionalDetails;
import com.prokarama.beans.Response;
import com.prokarama.beans.UpdateInsuranceCardDetailsRequest;
import com.prokarma.entity.EmployeeDetails;
import com.prokarma.entity.InsuranceCardDetails;
import com.prokarma.repositories.IEmployeeDetailsRepository;
import com.prokarma.repositories.IInsuranceCardDetailsRepository;
import com.prokarma.rest.controller.IEmployeeInformationController;
import com.prokarma.rest.service.IEmployeeDetailsService;
import com.prokarma.rest.service.IInsuranceCardDetailsService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class InsuranceCardDetailsTests {
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
	private CreateInsuranceCardDetailsRequest createInsuranceCardDetailsRequest;
	private UpdateInsuranceCardDetailsRequest updateInsuranceCardDetailsRequest;
	
	@BeforeEach
	public void loadContext() {
		createInsuranceCardDetailsRequest=new CreateInsuranceCardDetailsRequest();
		updateInsuranceCardDetailsRequest=new UpdateInsuranceCardDetailsRequest();
		addingParameters(createInsuranceCardDetailsRequest);
		addingParametersUpdate(updateInsuranceCardDetailsRequest);
	}
	
	private void addingParameters(CreateInsuranceCardDetailsRequest createInsuranceCardDetailsRequest) {
		createInsuranceCardDetailsRequest.setDependentName("prokarma");
		createInsuranceCardDetailsRequest.setDob("22/04/1997");
		EmployeeAdditionalDetails employeeAdditionalDetails=new EmployeeAdditionalDetails();
		employeeAdditionalDetails.setAddress("warangal");
		employeeAdditionalDetails.setBloodGroup("A+");
	}
	private void addingParametersUpdate(UpdateInsuranceCardDetailsRequest updateInsuranceCardDetailsRequest) {
		updateInsuranceCardDetailsRequest.setDependentName("prokarma");
		updateInsuranceCardDetailsRequest.setDob("22/04/1997");
		EmployeeAdditionalDetails employeeAdditionalDetails=new EmployeeAdditionalDetails();
		employeeAdditionalDetails.setAddress("warangal");
		employeeAdditionalDetails.setBloodGroup("A+");
	}
	@Test
	public void addInsureCardDetailsTestNull() {
		ResponseEntity<Response<InsuranceCardDetails>> response=employeeInfromationController.createInsuranceCardDetails(null);
		Assert.assertEquals(response.getBody().getResponseCode(), "211");
	}
	@Test
	public void addInsureCardDetailsTestEmployeeNotFound() {
		Mockito.when(employeeDetailsRepository.findById(Mockito.any())).thenReturn(Optional.ofNullable(null));
		ResponseEntity<Response<InsuranceCardDetails>> response=employeeInfromationController.createInsuranceCardDetails(createInsuranceCardDetailsRequest);
		Assert.assertEquals(response.getBody().getResponseCode(), "2893");
	}
	
	@Test
	public void addInsureCardDetailsTestInvalidDate() {
		createInsuranceCardDetailsRequest.setDob("22-04-1997");
		Mockito.when(employeeDetailsRepository.findById(Mockito.any())).thenReturn(Optional.of(new EmployeeDetails()));
		ResponseEntity<Response<InsuranceCardDetails>> response=employeeInfromationController.createInsuranceCardDetails(createInsuranceCardDetailsRequest);
		Assert.assertEquals(response.getBody().getResponseCode(), "2892");
	}
	@Test
	public void addInsureCardDetailsTestHibernateException() {
		Mockito.when(insuranceCardDetailsRepository.save(Mockito.any())).thenThrow(new HibernateException("exception"));
		Mockito.when(employeeDetailsRepository.findById(Mockito.any())).thenReturn(Optional.of(new EmployeeDetails()));
		ResponseEntity<Response<InsuranceCardDetails>> response=employeeInfromationController.createInsuranceCardDetails(createInsuranceCardDetailsRequest);
		Assert.assertEquals(response.getBody().getResponseCode(), "2891");
	}
	@Test
	public void addInsureCardDetailsTestHibernateSuccess() {
		Mockito.when(insuranceCardDetailsRepository.save(Mockito.any())).thenReturn(new InsuranceCardDetails());
		doNothing().when(jmsTemplate).convertAndSend(Mockito.any());
		Mockito.when(employeeDetailsRepository.findById(Mockito.any())).thenReturn(Optional.of(new EmployeeDetails()));
		ResponseEntity<Response<InsuranceCardDetails>> response=employeeInfromationController.createInsuranceCardDetails(createInsuranceCardDetailsRequest);
		Assert.assertEquals(response.getBody().getResponseCode(), "0000");
	}
	@Test
	public void updateInsureCardDetailsTestNull() {
		ResponseEntity<Response<InsuranceCardDetails>> response=employeeInfromationController.updateInsuranceCardDetails(null);
		Assert.assertEquals(response.getBody().getResponseCode(), "211");
	}
	
	@Test
	public void updateInsureCardDetailsTestEmployeeNotFound() {
		Mockito.when(employeeDetailsRepository.findById(Mockito.any())).thenReturn(Optional.ofNullable(null));
		ResponseEntity<Response<InsuranceCardDetails>> response=employeeInfromationController.updateInsuranceCardDetails(updateInsuranceCardDetailsRequest);
		Assert.assertEquals(response.getBody().getResponseCode(), "2893");
	}
	@Test
	public void updateInsureCardDetailsTestInsuranceNotFound() {
		Mockito.when(employeeDetailsRepository.findById(Mockito.any())).thenReturn(Optional.of(new EmployeeDetails()));
		Mockito.when(insuranceCardDetailsRepository.findById(Mockito.any())).thenReturn(Optional.ofNullable(null));
		ResponseEntity<Response<InsuranceCardDetails>> response=employeeInfromationController.updateInsuranceCardDetails(updateInsuranceCardDetailsRequest);
		Assert.assertEquals(response.getBody().getResponseCode(), "2893");
	}
	@Test
	public void updateInsureCardDetailsTestInvalidDate() {
		updateInsuranceCardDetailsRequest.setDob("22-04-1997");
		Mockito.when(insuranceCardDetailsRepository.findById(Mockito.any())).thenReturn(Optional.of(new InsuranceCardDetails()));
		Mockito.when(employeeDetailsRepository.findById(Mockito.any())).thenReturn(Optional.of(new EmployeeDetails()));
		ResponseEntity<Response<InsuranceCardDetails>> response=employeeInfromationController.updateInsuranceCardDetails(updateInsuranceCardDetailsRequest);
		Assert.assertEquals(response.getBody().getResponseCode(), "2892");
	}
	@Test
	public void updateInsureCardDetailsTestHibernateException() {
		Mockito.when(insuranceCardDetailsRepository.findById(Mockito.any())).thenReturn(Optional.of(new InsuranceCardDetails()));
		Mockito.when(insuranceCardDetailsRepository.save(Mockito.any())).thenThrow(new HibernateException("exception"));
		Mockito.when(employeeDetailsRepository.findById(Mockito.any())).thenReturn(Optional.of(new EmployeeDetails()));
		ResponseEntity<Response<InsuranceCardDetails>> response=employeeInfromationController.updateInsuranceCardDetails(updateInsuranceCardDetailsRequest);
		Assert.assertEquals(response.getBody().getResponseCode(), "2891");
	}
	@Test
	public void updateInsureCardDetailsTestHibernateSuccess() {
		Mockito.when(insuranceCardDetailsRepository.findById(Mockito.any())).thenReturn(Optional.of(new InsuranceCardDetails()));
		Mockito.when(insuranceCardDetailsRepository.save(Mockito.any())).thenReturn(new InsuranceCardDetails());
		Mockito.when(employeeDetailsRepository.findById(Mockito.any())).thenReturn(Optional.of(new EmployeeDetails()));
		ResponseEntity<Response<InsuranceCardDetails>> response=employeeInfromationController.updateInsuranceCardDetails(updateInsuranceCardDetailsRequest);
		Assert.assertEquals(response.getBody().getResponseCode(), "0000");
	}
}
