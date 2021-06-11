package com.prokarma.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
@Entity
@Table(name="EMAIL_QUEUE", schema="EMPLOYEE")
public class EmailQueue {
private String emailId;
private String emailTemplate;
private String fromAddress;
private String toAddress;
private String emailSubject;
private String emailBody;
private Date createdOnLocal;
private String emailStatus;
private String customField;
private String customField2;
private Date customField3;
private String contentType;
/**
 * @return the emailId
 */
@Id
@Column(name="EMAIL_ID", nullable=false, length=100)
public String getEmailId() {
	return emailId;
}
/**
 * @param emailId the emailId to set
 */
public void setEmailId(String emailId) {
	this.emailId = emailId;
}

/**
 * @return the fromAddress
 */
@Column(name="FROM_ADDRESS", nullable=false, length=100)
public String getFromAddress() {
	return fromAddress;
}
/**
 * @param fromAddress the fromAddress to set
 */
public void setFromAddress(String fromAddress) {
	this.fromAddress = fromAddress;
}
/**
 * @return the toAddress
 */
@Column(name="TO_ADDRESS", nullable=false, length=100)
public String getToAddress() {
	return toAddress;
}
/**
 * @param toAddress the toAddress to set
 */
public void setToAddress(String toAddress) {
	this.toAddress = toAddress;
}
/**
 * @return the emailSubject
 */
@Column(name="EMAIL_SUBJECT", nullable=false, length=200)
public String getEmailSubject() {
	return emailSubject;
}
/**
 * @param emailSubject the emailSubject to set
 */
public void setEmailSubject(String emailSubject) {
	this.emailSubject = emailSubject;
}
/**
 * @return the emailBody
 */
@Column(name="EMAIL_BODY", nullable=false)
public String getEmailBody() {
	return emailBody;
}
/**
 * @param emailBody the emailBody to set
 */
public void setEmailBody(String emailBody) {
	this.emailBody = emailBody;
}
/**
 * @return the createdOnLocal
 */
@Column(name="CREATED_ON_LOCAL")
public Date getCreatedOnLocal() {
	return createdOnLocal;
}
/**
 * @param createdOnLocal the createdOnLocal to set
 */
public void setCreatedOnLocal(Date createdOnLocal) {
	this.createdOnLocal = createdOnLocal;
}
/**
 * @return the emailStatus
 */
@Column(name="EMAIL_STATUS")
public String getEmailStatus() {
	return emailStatus;
}
/**
 * @param emailStatus the emailStatus to set
 */
public void setEmailStatus(String emailStatus) {
	this.emailStatus = emailStatus;
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
 * @return the emailTemplate
 */
public String getEmailTemplate() {
	return emailTemplate;
}
/**
 * @param emailTemplate the emailTemplate to set
 */
public void setEmailTemplate(String emailTemplate) {
	this.emailTemplate = emailTemplate;
}
/**
 * @return the contentType
 */
@Transient
public String getContentType() {
	return contentType;
}
/**
 * @param contentType the contentType to set
 */
public void setContentType(String contentType) {
	this.contentType = contentType;
}
}
