package com.prokarma.rest.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.prokarama.beans.CreateEmployeeRequest;
import com.prokarama.beans.Response;
import com.prokarama.beans.UpdateEmployeeRequest;
import com.prokarma.entity.EmployeeDetails;

@Service
public interface IEmployeeDetailsService {

	public Response<EmployeeDetails> addEmployeeDetails(CreateEmployeeRequest createEmployeeRequest);
	
	public Response<EmployeeDetails> updateEmployeeDetails(UpdateEmployeeRequest updateEmployeeRequest);
	
	public Response<EmployeeDetails> getEmployeeDetails(String employeeId);
	
	public Response<List<EmployeeDetails>> getAllEmployeeDetails();
	
	public Response<EmployeeDetails> deactiveEmployeeDetails(String employeeId);

}
