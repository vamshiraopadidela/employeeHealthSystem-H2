package com.prokarma.rest.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prokarama.beans.CreateEmployeeRequest;
import com.prokarama.beans.CreateInsuranceCardDetailsRequest;
import com.prokarama.beans.Response;
import com.prokarama.beans.UpdateEmployeeRequest;
import com.prokarama.beans.UpdateInsuranceCardDetailsRequest;
import com.prokarma.entity.EmployeeDetails;
import com.prokarma.entity.InsuranceCardDetails;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;

@RestController
@Api(value = "EmployeeInformationController", description = "REST APIs related to Employees")
@RequestMapping(value="/employee/")
public interface IEmployeeInformationController {

	@PostMapping(value="/create")
	@ApiOperation(value = "Onboarding the employee in System ")
	@ApiResponses(value = {
	        @ApiResponse(code = 500, message = "Server error"),
	         @ApiResponse(code = 404, message = "Service not found"),
	        @ApiResponse(code = 200, message = "Successful retrieval"
	            ) })	
	public ResponseEntity<Response<EmployeeDetails>> createEmployeeDetails(@Valid @RequestBody CreateEmployeeRequest createEmployeeRequest);

	@GetMapping(value="/read/{employeeId}")
	@ApiOperation(value = "Getting the employee  Details using employeeId ")
	@ApiResponses(value = {
	        @ApiResponse(code = 500, message = "Server error"),
	         @ApiResponse(code = 404, message = "Service not found"),
	        @ApiResponse(code = 200, message = "Successful retrieval"
	            ) })
	public ResponseEntity<Response<EmployeeDetails>> getEmployeeDetails(@PathVariable String employeeId);
	
	@GetMapping("/all")
	@ApiOperation(value = "Getting all Employee Details")
	@ApiResponses(value = {
	        @ApiResponse(code = 500, message = "Server error"),
	         @ApiResponse(code = 404, message = "Service not found"),
	        @ApiResponse(code = 200, message = "Successful retrieval"
	            ) })
	public ResponseEntity<Response<List<EmployeeDetails>>>  getAllEmployeeDetails();
	
	@PostMapping(value="/update")
	@ApiOperation(value = "Updating the Employee Details")
	@ApiResponses(value = {
	        @ApiResponse(code = 500, message = "Server error"),
	         @ApiResponse(code = 404, message = "Service not found"),
	        @ApiResponse(code = 200, message = "Successful retrieval"
	            ) })
	public ResponseEntity<Response<EmployeeDetails>> updateEmployeeDetails(@Valid @RequestBody UpdateEmployeeRequest updateEmployeeRequest);

	@GetMapping(value="/delete/{employeeId}")
	@ApiOperation(value = "deactiving the Employee Details by employee Id")
	@ApiResponses(value = {
	        @ApiResponse(code = 500, message = "Server error"),
	         @ApiResponse(code = 404, message = "Service not found"),
	        @ApiResponse(code = 200, message = "Successful retrieval"
	            ) })
	public ResponseEntity<Response<EmployeeDetails>> deactiveEmployeeDetails(@PathVariable String employeeId);
	
	@PostMapping(value="/upload/insurance")
	@ApiOperation(value = "Uploading the Insurance Details")
	@ApiResponses(value = {
	        @ApiResponse(code = 500, message = "Server error"),
	         @ApiResponse(code = 404, message = "Service not found"),
	        @ApiResponse(code = 200, message = "Successful retrieval"
	            ) })
	public ResponseEntity<Response<InsuranceCardDetails>> createInsuranceCardDetails(@Valid @RequestBody CreateInsuranceCardDetailsRequest insuranceCardDetailsRequest);

	@PostMapping(value="/update/insurance")
	@ApiOperation(value = "Updating the Insurance Details")
	@ApiResponses(value = {
	        @ApiResponse(code = 500, message = "Server error"),
	         @ApiResponse(code = 404, message = "Service not found"),
	        @ApiResponse(code = 200, message = "Successful retrieval"
	            ) })
	public ResponseEntity<Response<InsuranceCardDetails>> updateInsuranceCardDetails(@Valid @RequestBody UpdateInsuranceCardDetailsRequest insuranceCardDetailsRequest);
 
}
