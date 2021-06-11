package com.prokarma.common;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.apache.commons.text.StringSubstitutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.prokarma.entity.EmailQueue;
import com.prokarma.entity.EmailTemplate;
import com.prokarma.repositories.IEmailTemplateRepository;


@Component
public class CommonUtils {
	private static final Logger logger = LoggerFactory.getLogger(CommonUtils.class);
	@Autowired
	private IEmailTemplateRepository emailTemplateRepository;


	public static void copyProperties(Object source, Object destination) {
			if(source!=null||destination!=null) {
				Method[] sourceMethods=source.getClass().getMethods();
				List<Method> sourceMethodsList=Arrays.stream(sourceMethods).filter(getMethodPredicate(source)).collect(Collectors.toList());
				sourceMethodsList.stream().forEach(e->{
					try {
						Object input=e.invoke(source);
						destination.getClass().getMethod(getMethodName(e.getName()),input.getClass()).invoke(destination, input);
					} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e1) {
						logger.debug("unable to invoke method ",e1);
					}
				});
			}
	}

	private static String getMethodName(String name) {
		return "set"+name.substring(3);
	}
	
	public static boolean isEmpty(@Nullable Object str) {
		return (str == null || "".equals(str));
	}
	private static Predicate<Method> getMethodPredicate(Object source) {
		Predicate<Method> sourceMethodPredicate =  x -> {
			try {
				return x.getName().startsWith("get")&&!x.getName().equals("getClass")&&x.invoke(source)!=null;
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				logger.error("unable to invoke method",e);
			}
			return false;
		};			
		return sourceMethodPredicate;

	}

	public void fillEmailBody(EmailQueue emailQueue, Object src) {
		List<EmailTemplate> emailTemplate=emailTemplateRepository.findByEmailTemplate(emailQueue.getEmailTemplate());
		String template=emailTemplate.get(0).getTemplate();
		String subject=emailTemplate.get(0).getEmailSubject();
		if(!CommonUtils.isEmpty(emailQueue.getEmailBody())) {
			template=emailQueue.getEmailBody();
			subject=emailQueue.getEmailSubject();
		}
        ObjectMapper oMapper = new ObjectMapper();
		oMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
		Map<String, Object> mapFields = oMapper.convertValue(src, Map.class);
		String emailFilledBody=StringSubstitutor.replace(template,mapFields, "[", "]");
		String emailFilledSubject=StringSubstitutor.replace(subject,mapFields, "[", "]");
		emailQueue.setEmailBody(emailFilledBody);
		emailQueue.setEmailSubject(emailFilledSubject);
		}

	/**
	 * @return the emailTemplateRepository
	 */
	public IEmailTemplateRepository getEmailTemplateRepository() {
		return emailTemplateRepository;
	}

	/**
	 * @param emailTemplateRepository the emailTemplateRepository to set
	 */
	public void setEmailTemplateRepository(IEmailTemplateRepository emailTemplateRepository) {
		this.emailTemplateRepository = emailTemplateRepository;
	}



}
