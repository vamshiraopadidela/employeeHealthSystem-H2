package com.prokarma.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="EMAIL_TEMPLATE", schema="EMPLOYEE")
public class EmailTemplate {
	
	private String emailTemplateId;
	private String emailTemplate;
	private String template;
	private String emailSubject;
	/**
	 * @return the emailTemplateId
	 */
	@Id
	@Column(name="EMAIL_TEMPLATE_ID", nullable=false, length=100)
	public String getEmailTemplateId() {
		return emailTemplateId;
	}
	/**
	 * @param emailTemplateId the emailTemplateId to set
	 */
	public void setEmailTemplateId(String emailTemplateId) {
		this.emailTemplateId = emailTemplateId;
	}
	/**
	 * @return the emailTemplate
	 */
	@Column(name="EMAIL_TEMPLATE")
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
	 * @return the template
	 */
	@Column(name="TEMPLATE")
	public String getTemplate() {
		return template;
	}
	/**
	 * @param template the template to set
	 */
	public void setTemplate(String template) {
		this.template = template;
	}
	/**
	 * @return the emailSubject
	 */
	@Column(name="EMAIL_SUBJECT")
	public String getEmailSubject() {
		return emailSubject;
	}
	/**
	 * @param emailSubject the emailSubject to set
	 */
	public void setEmailSubject(String emailSubject) {
		this.emailSubject = emailSubject;
	}

}
