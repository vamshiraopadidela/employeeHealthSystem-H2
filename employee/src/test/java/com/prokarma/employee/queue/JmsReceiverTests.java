package com.prokarma.employee.queue;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.prokarma.activemq.listener.JmsMessageReceiver;
import com.prokarma.common.CommonUtils;
import com.prokarma.entity.EmailQueue;
import com.prokarma.entity.EmailTemplate;
import com.prokarma.entity.EmployeeDetails;
import com.prokarma.entity.InsuranceCardDetails;
import com.prokarma.mail.SmtpEmail;
import com.prokarma.repositories.IEmailQueueRepository;
import com.prokarma.repositories.IEmailTemplateRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class JmsReceiverTests {
	@Autowired
	private JmsMessageReceiver jmsMessageReceiver;
	
	private EmployeeDetails employeeDetails;
	@MockBean
	private CommonUtils commonUtils;
	@MockBean
	private IEmailQueueRepository emailQueueRepository;
	@MockBean
	private JmsTemplate jmsTemplate;
	@MockBean
	private IEmailTemplateRepository emailTemplateRepository;
	@Autowired
	private SmtpEmail SmtpEmail;
	
	private EmailTemplate emailTemplate;
	private InsuranceCardDetails insuranceCardDetails;
	private EmailQueue emailQueue;

	
	@BeforeEach
	public void loadEmployeeDetails() {
	employeeDetails=new EmployeeDetails();
	employeeDetails.setEmail("prokarma@gmail.com");
	employeeDetails.setEmployeeName("prokarma1");
	emailTemplate=new EmailTemplate();
	emailTemplate.setEmailSubject("Welcom prokarma");
	emailTemplate.setTemplate("Test Mail");
	emailTemplate.setEmailTemplate("MailTemplate");
	insuranceCardDetails=new InsuranceCardDetails();
	insuranceCardDetails.setEmployeeDetails(employeeDetails);
	emailQueue=new EmailQueue();
	emailQueue.setContentType("TEXT/PLAIN");
	emailQueue.setFromAddress("prokarmafrom@gmail.com");
	emailQueue.setToAddress("prokarmato@gmail.com");
	emailQueue.setEmailSubject(emailTemplate.getEmailSubject());
	emailQueue.setEmailBody(emailTemplate.getTemplate());
	}
	
	@Test
	public void createEmployeeListnerTest() {
		List<EmailTemplate> emailTemplateList=new ArrayList<>();
		emailTemplateList.add(this.emailTemplate);
		doNothing().when(jmsTemplate).convertAndSend(Mockito.any());
		Mockito.when(emailTemplateRepository.findByEmailTemplate(Mockito.any())).thenReturn(emailTemplateList);
		Mockito.when(emailQueueRepository.save(Mockito.any())).thenReturn(null);
		jmsMessageReceiver.createEmployeeListner(employeeDetails);
	}
	@Test
	public void createInsuranceListnerTest() {
		List<EmailTemplate> emailTemplateList=new ArrayList<>();
		emailTemplateList.add(this.emailTemplate);
		doNothing().when(jmsTemplate).convertAndSend(Mockito.any());
		Mockito.when(emailTemplateRepository.findByEmailTemplate(Mockito.any())).thenReturn(emailTemplateList);
		Mockito.when(emailQueueRepository.save(Mockito.any())).thenReturn(null);
		jmsMessageReceiver.createInsuranceListner(insuranceCardDetails);
	}
	@Test
	public void deactiveInsuranceListnerTest() {
		List<EmailTemplate> emailTemplateList=new ArrayList<>();
		emailTemplateList.add(this.emailTemplate);
		doNothing().when(jmsTemplate).convertAndSend(Mockito.any());
		Mockito.when(emailTemplateRepository.findByEmailTemplate(Mockito.any())).thenReturn(emailTemplateList);
		Mockito.when(emailQueueRepository.save(Mockito.any())).thenReturn(null);
		jmsMessageReceiver.deactiveInsuranceListner(insuranceCardDetails);
	}
	
	
	@Test
	public void emailExceptionTest(){
		Mockito.when(emailQueueRepository.save(Mockito.any())).thenReturn(emailQueue);
		jmsMessageReceiver.sendingMails(emailQueue);
		
	}
	
}
