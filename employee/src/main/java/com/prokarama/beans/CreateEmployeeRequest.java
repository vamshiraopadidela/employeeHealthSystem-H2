package com.prokarama.beans;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;


public class CreateEmployeeRequest implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3648578553402800521L;
	@NotBlank(message = "Employee name is mandatory")
	private String employeeName;
	private String dob;
	private String designation;
	private String workLocation;
	@NotBlank(message = "Mobile is mandatory")
	private String mobile;
	@NotBlank(message = "Email is mandatory")
	private String email;
	private EmployeeAdditionalDetails additionalDetails;


	/**
	 * @return the employeeName
	 */
	public String getEmployeeName() {
		return employeeName;
	}
	/**
	 * @param employeeName the employeeName to set
	 */
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	/**
	 * @return the designation
	 */
	public String getDesignation() {
		return designation;
	}
	/**
	 * @param designation the designation to set
	 */
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	/**
	 * @return the workLocation
	 */
	public String getWorkLocation() {
		return workLocation;
	}
	/**
	 * @param workLocation the workLocation to set
	 */
	public void setWorkLocation(String workLocation) {
		this.workLocation = workLocation;
	}
	/**
	 * @return the mobile
	 */
	public String getMobile() {
		return mobile;
	}
	/**
	 * @param mobile the mobile to set
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the additionalDetails
	 */
	public EmployeeAdditionalDetails getAdditionalDetails() {
		return additionalDetails;
	}
	/**
	 * @param additionalDetails the additionalDetails to set
	 */
	public void setAdditionalDetails(EmployeeAdditionalDetails additionalDetails) {
		this.additionalDetails = additionalDetails;
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

}
