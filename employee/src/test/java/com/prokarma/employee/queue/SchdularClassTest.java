package com.prokarma.employee.queue;

import static org.mockito.Mockito.doNothing;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.prokarma.entity.EmployeeDetails;
import com.prokarma.entity.InsuranceCardDetails;
import com.prokarma.repositories.IInsuranceCardDetailsRepository;
import com.prokarma.scheduling.InsuranceSchedular;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class SchdularClassTest {
	private InsuranceCardDetails insuranceCardDetails;
	private EmployeeDetails employeeDetails;
	@MockBean
	private IInsuranceCardDetailsRepository insuranceCardDetailsRepository;
	
	@Autowired
	private InsuranceSchedular insuranceSch;

	@BeforeEach
	public void loadInsuraceCardDetails() {
		employeeDetails=new EmployeeDetails();
		employeeDetails.setEmail("prokarma@gmail.com");
		employeeDetails.setEmployeeName("prokarma1");
		insuranceCardDetails=new InsuranceCardDetails();
		insuranceCardDetails.setEmployeeDetails(employeeDetails);
	}
	
	@Test
	public void testSchdularNull() {
		List<InsuranceCardDetails> insuranceCardDetailsList=new ArrayList<>();
		insuranceCardDetailsList.add(insuranceCardDetails);
		Mockito.when(insuranceCardDetailsRepository.getExpiredInsuranceRecords(Mockito.any(),Mockito.any())).thenReturn(null);
		doNothing().when(insuranceCardDetailsRepository).deleteAll(Mockito.any());
		insuranceSch.schedularInsurance();
	}
	@Test
	public void testSchdularSuccess() {
		List<InsuranceCardDetails> insuranceCardDetailsList=new ArrayList<>();
		insuranceCardDetailsList.add(insuranceCardDetails);
		Mockito.when(insuranceCardDetailsRepository.getExpiredInsuranceRecords(Mockito.any(),Mockito.any())).thenReturn(insuranceCardDetailsList);
		doNothing().when(insuranceCardDetailsRepository).deleteAll(Mockito.any());
		insuranceSch.schedularInsurance();
	}
}
