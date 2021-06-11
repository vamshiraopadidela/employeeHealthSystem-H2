package com.prokarma.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="EMPLOYEE_DETAILS", schema="EMPLOYEE")
public class EmployeeDetails {
private String employeeId;
private String employeeName;
private Date dob;
private String designation;
private Date dateOfJoining;
private String workLocation;
private String mobile;
private String email;
private Date dateOfLeaving;
private String addtionalDetails;
private String customField;
private String customField2;
private Date customField3;
private String employeeStatus;

/**
 * Default Constructor of Employee Details
 */
public EmployeeDetails() {
	super();
}
/**
 * @return the employeeId
 */
@Id
@Column(name="EMPLOYEE_ID", nullable=false, length=100)
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
 * @return the employeeName
 */
@Column(name="EMPLOYEE_NAME", nullable=false, length=100)
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
 * @return the dob
 */
@Column(name="DOB", nullable=false)
public Date getDob() {
	return dob;
}
/**
 * @param dob the dob to set
 */
public void setDob(Date dob) {
	this.dob = dob;
}
/**
 * @return the designation
 */
@Column(name="DESIGNATION", nullable=false, length=50)
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
 * @return the dateOfJoining
 */
@Column(name="DATE_OF_JOINING", nullable=false)
public Date getDateOfJoining() {
	return dateOfJoining;
}
/**
 * @param dateOfJoining the dateOfJoining to set
 */
public void setDateOfJoining(Date dateOfJoining) {
	this.dateOfJoining = dateOfJoining;
}
/**
 * @return the workLocation
 */
@Column(name="WORK_LOCATION", length=100)
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
@Column(name="mobile", length=20)
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
@Column(name="EMAIL", length=50)
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
 * @return the dateOfLeaving
 */
@Column(name="DATE_OF_LEAVING")
public Date getDateOfLeaving() {
	return dateOfLeaving;
}
/**
 * @param dateOfLeaving the dateOfLeaving to set
 */
public void setDateOfLeaving(Date dateOfLeaving) {
	this.dateOfLeaving = dateOfLeaving;
}
/**
 * @return the addtionalDetails
 */
@Column(name="ADDTIONAL_DETAILS", length=500)
public String getAddtionalDetails() {
	return addtionalDetails;
}
/**
 * @param addtionalDetails the addtionalDetails to set
 */
public void setAddtionalDetails(String addtionalDetails) {
	this.addtionalDetails = addtionalDetails;
}
/**
 * @return the customField
 */
@Column(name="CUSTOM_FIELD", length=100)
public String getCustomField() {
	return customField;
}
/**
 * @param customField the customField to set
 */
public void setCustomField(String customField) {
	this.customField = customField;
}
/**
 * @return the customField2
 */
@Column(name="CUSTOM_FIELD2", length=10)
public String getCustomField2() {
	return customField2;
}
/**
 * @param customField2 the customField2 to set
 */
public void setCustomField2(String customField2) {
	this.customField2 = customField2;
}
/**
 * @return the customField3
 */
@Column(name="CUSTOM_FIELD3")
public Date getCustomField3() {
	return customField3;
}
/**
 * @param customField3 the customField3 to set
 */
public void setCustomField3(Date customField3) {
	this.customField3 = customField3;
}
/**
 * @return the employeeStatus
 */
@Column(name="EMPLOYEE_STATUS")
public String getEmployeeStatus() {
	return employeeStatus;
}
/**
 * @param employeeStatus the employeeStatus to set
 */
public void setEmployeeStatus(String employeeStatus) {
	this.employeeStatus = employeeStatus;
}
@Override
public String toString() {
	return "EmployeeDetails [employeeId=" + employeeId + ", employeeName=" + employeeName + ", dob=" + dob
			+ ", designation=" + designation + ", dateOfJoining=" + dateOfJoining + ", workLocation=" + workLocation
			+ ", mobile=" + mobile + ", email=" + email + ", dateOfLeaving=" + dateOfLeaving + ", addtionalDetails="
			+ addtionalDetails + ", customField=" + customField + ", customField2=" + customField2 + ", customField3="
			+ customField3 + ", employeeStatus=" + employeeStatus + "]";
}

}
