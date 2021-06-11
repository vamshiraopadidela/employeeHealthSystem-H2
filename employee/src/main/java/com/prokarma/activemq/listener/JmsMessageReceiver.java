package com.prokarma.activemq.listener;

import java.util.Calendar;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import com.prokarma.common.CommonUtils;
import com.prokarma.common.SequenceGenerator;
import com.prokarma.entity.EmailQueue;
import com.prokarma.entity.EmailTemplate;
import com.prokarma.entity.EmployeeDetails;
import com.prokarma.entity.InsuranceCardDetails;
import com.prokarma.errorcodes.ModuleConstants;
import com.prokarma.mail.SmtpEmail;
import com.prokarma.repositories.IEmailQueueRepository;
import com.prokarma.repositories.IEmailTemplateRepository;


@Component
public class JmsMessageReceiver {
	private static final Logger logger = LoggerFactory.getLogger(JmsMessageReceiver.class);
	
	@Value("${org.fromaddress}")
	 private String fromAddress;
	
	@Value("${org.insurance.toaddress}")
	private String toAddress;
	
	@Autowired
	private JmsTemplate jmsTemplate;
	
	@Autowired
	private IEmailQueueRepository emailQueueRepository;
	
	@Autowired
	private CommonUtils commonUtils;
	
	
	@Autowired
	private SmtpEmail SmtpEmail;

	@JmsListener(destination = "createEmployee", containerFactory = "JmsConnectionFactory")
	public void createEmployeeListner(EmployeeDetails employeeDetails) {
		logger.info(employeeDetails.toString());
		EmailQueue emailQueue=new EmailQueue();
		emailQueue.setEmailTemplate(ModuleConstants.EMPLOYEE_INSURANC_REQUEST.getValue());
		commonUtils.fillEmailBody(emailQueue,employeeDetails);
		emailQueue.setContentType("TEXT/PLAIN");
		emailQueue.setToAddress(employeeDetails.getEmail());
		emailQueue.setFromAddress(fromAddress);
		emailQueue.setEmailId(SequenceGenerator.generateEmailSequence());
		emailQueue.setCreatedOnLocal(Calendar.getInstance().getTime());
		emailQueue.setEmailStatus(ModuleConstants.ISSUED.getValue());
		emailQueueRepository.save(emailQueue);
		jmsTemplate.convertAndSend("mail",emailQueue);
	}
	@JmsListener(destination = "createInsurance", containerFactory = "JmsConnectionFactory")
	public void createInsuranceListner(InsuranceCardDetails insuranceCardDetails) {
		logger.info(insuranceCardDetails.toString());
		EmailQueue emailQueue=new EmailQueue();
		emailQueue.setEmailTemplate(ModuleConstants.BROKER_INSURANC_REQUEST.getValue());
		commonUtils.fillEmailBody(emailQueue,insuranceCardDetails);
		commonUtils.fillEmailBody(emailQueue,insuranceCardDetails.getEmployeeDetails());
		emailQueue.setContentType("TEXT/HTML");
		emailQueue.setToAddress(toAddress);
		emailQueue.setFromAddress(fromAddress);
		emailQueue.setEmailId(SequenceGenerator.generateEmailSequence());
		emailQueue.setCreatedOnLocal(Calendar.getInstance().getTime());
		emailQueue.setEmailStatus(ModuleConstants.ISSUED.getValue());
		emailQueueRepository.save(emailQueue);
		jmsTemplate.convertAndSend("mail",emailQueue);
	}
	@JmsListener(destination = "deleteEmployee", containerFactory = "JmsConnectionFactory")
	public void deactiveInsuranceListner(InsuranceCardDetails insuranceCardDetails) {
			EmailQueue emailQueue=new EmailQueue();
			emailQueue.setEmailTemplate(ModuleConstants.BROKER_DEACTIVATE_REQUEST.getValue());
			commonUtils.fillEmailBody(emailQueue,insuranceCardDetails);
			commonUtils.fillEmailBody(emailQueue,insuranceCardDetails.getEmployeeDetails());
			emailQueue.setContentType("TEXT/HTML");
			emailQueue.setToAddress(toAddress);
			emailQueue.setFromAddress(fromAddress);
			emailQueue.setEmailId(SequenceGenerator.generateEmailSequence());
			emailQueue.setCreatedOnLocal(Calendar.getInstance().getTime());
			emailQueue.setEmailStatus(ModuleConstants.ISSUED.getValue());
			emailQueueRepository.save(emailQueue);
			jmsTemplate.convertAndSend("mail",emailQueue);
		}

	
	@JmsListener(destination = "mail", containerFactory = "JmsConnectionFactory")
	public void sendingMails(EmailQueue emailQueue) {
		boolean isSent=SmtpEmail.sendSmtpEmail(emailQueue);
		if(isSent) {
			emailQueue.setEmailStatus(ModuleConstants.DELIVERED.getValue());
		}else {
			emailQueue.setEmailStatus(ModuleConstants.UNDELIVERED.getValue());

		}
		emailQueueRepository.save(emailQueue);
	}

	/**
	 * @return the emailQueueRepository
	 */
	public IEmailQueueRepository getEmailQueueRepository() {
		return emailQueueRepository;
	}

	/**
	 * @param emailQueueRepository the emailQueueRepository to set
	 */
	public void setEmailQueueRepository(IEmailQueueRepository emailQueueRepository) {
		this.emailQueueRepository = emailQueueRepository;
	}



}
