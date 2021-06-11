package com.prokarma.mail;


import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Component;

import com.prokarma.entity.EmailQueue;
@Component
public class SmtpEmail {

	public Boolean sendSmtpEmail(EmailQueue emailqueue) {
		
		final String fromAddress=emailqueue.getFromAddress();
		final String toAddress=emailqueue.getToAddress();
		final Properties prop=new Properties();
         prop.put("mail.smtp.auth", "true");
         prop.put("mail.smtp.host", "smtp.gmail.com");
         prop.put("mail.smtp.port", "587");
         prop.put("mail.smtp.starttls.enable", "true");
         prop.put("mail.smtp.connectiontimeout","5000");
         prop.put("mail.smtp.timeout","180000");
         String password="Y29vbEAxMjM=";
         Session session = Session.getDefaultInstance(prop,
                 new Authenticator() {
                   protected PasswordAuthentication getPasswordAuthentication() {
                       return new PasswordAuthentication("rajeshdaggu12@gmail.com",new String(Base64.getDecoder().decode(password.getBytes(StandardCharsets.UTF_8))));
                 }
               });
         final Message message=new MimeMessage(session);
         try {
        	 message.setFrom(new InternetAddress(fromAddress,"Prokarma"));
        	 message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toAddress));
        	 message.setSubject(emailqueue.getEmailSubject());
        	 MimeBodyPart messageBodyPart=new MimeBodyPart();
        	 messageBodyPart.setText(emailqueue.getEmailBody());
        	 message.setContent(emailqueue.getEmailBody(),emailqueue.getContentType());
        	 Transport.send(message);
        	 return true;
         }catch(final MessagingException|UnsupportedEncodingException e) {
        	 return false;
         }
	}
}
