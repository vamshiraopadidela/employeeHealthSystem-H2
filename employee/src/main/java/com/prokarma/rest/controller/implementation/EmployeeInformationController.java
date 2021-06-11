/**
 * 
 */
package com.prokarma.rest.controller.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.prokarama.beans.CreateEmployeeRequest;
import com.prokarama.beans.CreateInsuranceCardDetailsRequest;
import com.prokarama.beans.Response;
import com.prokarama.beans.UpdateEmployeeRequest;
import com.prokarama.beans.UpdateInsuranceCardDetailsRequest;
import com.prokarma.entity.EmployeeDetails;
import com.prokarma.entity.InsuranceCardDetails;
import com.prokarma.rest.controller.IEmployeeInformationController;
import com.prokarma.rest.service.IEmployeeDetailsService;
import com.prokarma.rest.service.IInsuranceCardDetailsService;

/**
 * @author pvamshi
 *
 */
@Component
public class EmployeeInformationController implements IEmployeeInformationController  {

	@Autowired
	private IEmployeeDetailsService employeeDetailsService;
	
	@Autowired
	private IInsuranceCardDetailsService insuranceCardDetailsService;
	
	@Override
	public ResponseEntity<Response<EmployeeDetails>> createEmployeeDetails(
			CreateEmployeeRequest createEmployeeRequest) {
		Response<EmployeeDetails> response=employeeDetailsService.addEmployeeDetails(createEmployeeRequest);
		return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<Response<EmployeeDetails>> getEmployeeDetails(String employeeId) {
		Response<EmployeeDetails> response=employeeDetailsService.getEmployeeDetails(employeeId);
		return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<Response<List<EmployeeDetails>>> getAllEmployeeDetails() {
		Response<List<EmployeeDetails>> response=employeeDetailsService.getAllEmployeeDetails();
		return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<Response<EmployeeDetails>> updateEmployeeDetails(
			UpdateEmployeeRequest updateEmployeeRequest) {
		Response<EmployeeDetails> response=employeeDetailsService.updateEmployeeDetails(updateEmployeeRequest);
		return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<Response<EmployeeDetails>> deactiveEmployeeDetails(String employeeId) {
		Response<EmployeeDetails> response=employeeDetailsService.deactiveEmployeeDetails(employeeId);
		return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<Response<InsuranceCardDetails>> createInsuranceCardDetails(
			CreateInsuranceCardDetailsRequest insuranceCardDetailsRequest) {
		Response<InsuranceCardDetails> response= insuranceCardDetailsService.addInsureCardDetails(insuranceCardDetailsRequest);
		return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<Response<InsuranceCardDetails>> updateInsuranceCardDetails(
			UpdateInsuranceCardDetailsRequest insuranceCardDetailsRequest) {
		Response<InsuranceCardDetails> response= insuranceCardDetailsService.updateInsureCardDetails(insuranceCardDetailsRequest);
		return ResponseEntity.ok(response);
	}

	/**
	 * @return the insuranceCardDetailsService
	 */
	public IInsuranceCardDetailsService getInsuranceCardDetailsService() {
		return insuranceCardDetailsService;
	}

	/**
	 * @param insuranceCardDetailsService the insuranceCardDetailsService to set
	 */
	public void setInsuranceCardDetailsService(IInsuranceCardDetailsService insuranceCardDetailsService) {
		this.insuranceCardDetailsService = insuranceCardDetailsService;
	}

	/**
	 * @return the employeeDetailsService
	 */
	public IEmployeeDetailsService getEmployeeDetailsService() {
		return employeeDetailsService;
	}

	/**
	 * @param employeeDetailsService the employeeDetailsService to set
	 */
	public void setEmployeeDetailsService(IEmployeeDetailsService employeeDetailsService) {
		this.employeeDetailsService = employeeDetailsService;
	}

}
