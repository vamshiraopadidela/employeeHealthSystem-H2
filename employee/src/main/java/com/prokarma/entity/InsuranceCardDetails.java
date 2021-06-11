package com.prokarma.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="INSURANCE_CARD_DETAILS", schema="EMPLOYEE")
public class InsuranceCardDetails {
	private String cardId;
	private EmployeeDetails employeeDetails;
	private String dependentName;
	private String relationToEmployee;
	private Date dob;
	private Date activateDate;
	private String additionalDetails;
	private String customField;
	private String customField2;
	private Date customField3;
	private String cardStatus;
	@Override
	public String toString() {
		return "InsuranceCardDetails [cardId=" + cardId + ", employeeDetails=" + employeeDetails + ", dependentName="
				+ dependentName + ", relationToEmployee=" + relationToEmployee + ", dob=" + dob + ", activateDate="
				+ activateDate + ", additionalDetails=" + additionalDetails + ", customField=" + customField
				+ ", customField2=" + customField2 + ", customField3=" + customField3 + ", cardStatus=" + cardStatus
				+ ", expirationDate=" + expirationDate + "]";
	}
	private Date expirationDate;
	/**
	 * Default Constructor for InsuranceCardDetails
	 */
	public InsuranceCardDetails() {
		super();
	}
	/**
	 * @return the cardId
	 */
	@Id
	@Column(name="CARD_ID", nullable=false, length=100)
	public String getCardId() {
		return cardId;
	}
	/**
	 * @param cardId the cardId to set
	 */
	public void setCardId(String cardId) {
		this.cardId = cardId;
	}
	/**
	 * @return the employeeDetails
	 */
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "EMPLOYEE_ID")
	public EmployeeDetails getEmployeeDetails() {
		return employeeDetails;
	}
	/**
	 * @param employeeDetails the employeeDetails to set
	 */
	public void setEmployeeDetails(EmployeeDetails employeeDetails) {
		this.employeeDetails = employeeDetails;
	}
	/**
	 * @return the dependentName
	 */
	@Column(name="DEPENDENT_NAME", nullable=false, length=50)
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
	@Column(name="RELATION_TO_EMPLOYEE", nullable=false, length=100)
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
	 * @return the activateDate
	 */
	@Column(name="ACTIVATION_DATE", nullable=false)
	public Date getActivateDate() {
		return activateDate;
	}
	/**
	 * @param activateDate the activateDate to set
	 */
	public void setActivateDate(Date activateDate) {
		this.activateDate = activateDate;
	}
	/**
	 * @return the additionalDetails
	 */
	@Column(name="ADDTIONAL_DETAILS", length=500)
	public String getAdditionalDetails() {
		return additionalDetails;
	}
	/**
	 * @param additionalDetails the additionalDetails to set
	 */
	public void setAdditionalDetails(String additionalDetails) {
		this.additionalDetails = additionalDetails;
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
	 * @return the cardStatus
	 */
	@Column(name="CARD_STATUS")
	public String getCardStatus() {
		return cardStatus;
	}
	/**
	 * @param cardStatus the cardStatus to set
	 */
	public void setCardStatus(String cardStatus) {
		this.cardStatus = cardStatus;
	}
	/**
	 * @return the expirationDate
	 */
	@Column(name="EXPIRATION_DATE")
	public Date getExpirationDate() {
		return expirationDate;
	}
	/**
	 * @param expirationDate the expirationDate to set
	 */
	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}
	

}
