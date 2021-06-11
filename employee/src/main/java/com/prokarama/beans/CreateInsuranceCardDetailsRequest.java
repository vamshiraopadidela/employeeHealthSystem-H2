package com.prokarama.beans;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotBlank;

public class CreateInsuranceCardDetailsRequest implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@NotBlank(message = "Dependent Name is mandatory")
	private String dependentName;
	@NotBlank(message = "Relation to Employee is mandatory")
	private String relationToEmployee;
	private String dob;
	@NotBlank(message = "Employee Id is mandatory")
	private String employeeId;
	private EmployeeAdditionalDetails additionalDetails;
	/**
	 * @return the dependentName
	 */
	public String getDependentName() {
		return dependentName;
	}
	/**
	 * @param dependentName the dependentName to set
	 */
	public void setDependentName(String dependentName) {
		this.dependentName = dependentName;
	}
	/**
	 * @return the relationToEmployee
	 */
	public String getRelationToEmployee() {
		return relationToEmployee;
	}
	/**
	 * @param relationToEmployee the relationToEmployee to set
	 */
	public void setRelationToEmployee(String relationToEmployee) {
		this.relationToEmployee = relationToEmployee;
	}
	/**
	 * @return the dob
	 */
	public String getDob() {
		return dob;
	}
	/**
	 * @param dob the dob to set
	 */
	public void setDob(String dob) {
		this.dob = dob;
	}
	/**
	 * @return the employeeId
	 */
	public String getEmployeeId() {
		return employeeId;
	}
	/**
	 * @param employeeId the employeeId to set
	 */
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	/**
	 * @return the addtionalDetails
	 */
	public EmployeeAdditionalDetails getAdditionalDetails() {
		return additionalDetails;
	}
	/**
	 * @param addtionalDetails the addtionalDetails to set
	 */
	public void setAdditionalDetails(EmployeeAdditionalDetails addtionalDetails) {
		this.additionalDetails = addtionalDetails;
	}

}
