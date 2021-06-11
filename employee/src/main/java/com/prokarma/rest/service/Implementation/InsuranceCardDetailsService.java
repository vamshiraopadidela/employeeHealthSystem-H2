package com.prokarma.rest.service.Implementation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

import org.hibernate.HibernateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import com.google.gson.Gson;
import com.prokarama.beans.CreateInsuranceCardDetailsRequest;
import com.prokarama.beans.EmployeeAdditionalDetails;
import com.prokarama.beans.Response;
import com.prokarama.beans.UpdateInsuranceCardDetailsRequest;
import com.prokarma.common.CommonUtils;
import com.prokarma.entity.EmployeeDetails;
import com.prokarma.entity.InsuranceCardDetails;
import com.prokarma.errorcodes.ErrorCodes;
import com.prokarma.errorcodes.ErrorMessages;
import com.prokarma.repositories.IEmployeeDetailsRepository;
import com.prokarma.repositories.IInsuranceCardDetailsRepository;
import com.prokarma.rest.service.IInsuranceCardDetailsService;
@Component
public class InsuranceCardDetailsService implements IInsuranceCardDetailsService{
	private static final Logger logger = LoggerFactory.getLogger(InsuranceCardDetailsService.class);
	@Autowired
	private Gson gson;
	@Autowired
	private IInsuranceCardDetailsRepository insuranceCardDetailsRepository;
	@Autowired
	private JmsTemplate jmsTemplate;
	
	@Autowired
	private IEmployeeDetailsRepository employeeDetailsRepository;
	
	@Override
	public Response<InsuranceCardDetails> addInsureCardDetails(
			CreateInsuranceCardDetailsRequest insuranceCardDetailsRequest) {
		Response<InsuranceCardDetails> res=new Response<>();
		if(ObjectUtils.isEmpty(insuranceCardDetailsRequest)) {
			res.setResponseCode(ErrorCodes.EmptyRequest.getValue());
			res.setResponseMessage(ErrorMessages.EmptyRequest.getValue());
		}else {
			Optional<EmployeeDetails> employeeDetails=employeeDetailsRepository.findById(insuranceCardDetailsRequest.getEmployeeId());
			if(employeeDetails.isPresent()) {
			InsuranceCardDetails insuranceCardDetails=new InsuranceCardDetails();
			try {
				BeanUtils.copyProperties(insuranceCardDetailsRequest, insuranceCardDetails);
				insuranceCardDetails.setEmployeeDetails(employeeDetails.get());
				logger.info("Copying of EmployeeDetails from Request processed");
				addingDefaultParms(insuranceCardDetails);
				EmployeeAdditionalDetails employeeAdditionalDetails=insuranceCardDetailsRequest.getAdditionalDetails();
				String additionalDetails=gson.toJson(employeeAdditionalDetails);
				insuranceCardDetails.setAdditionalDetails(additionalDetails);
				SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
				Date dob= formatter.parse(insuranceCardDetailsRequest.getDob());
				insuranceCardDetails.setDob(dob);
				insuranceCardDetailsRepository.save(insuranceCardDetails);
				jmsTemplate.convertAndSend("createInsurance",insuranceCardDetails);
				res.setJsonObject(insuranceCardDetails);
				res.setResponseCode(ErrorCodes.SUCCESS.getValue());
				res.setResponseMessage(ErrorMessages.SUCCESS.getValue());
			}catch(BeansException e) {
				res.setResponseCode(ErrorCodes.BeanException.getValue());
				res.setResponseMessage(ErrorMessages.BeanException.getValue());
				logger.error("Unable to copy request to employee details",e);
			}catch(HibernateException e) {
				res.setResponseCode(ErrorCodes.HibernateException.getValue());
				res.setResponseMessage(ErrorMessages.HibernateException.getValue());
				logger.error("Unable to save employee details",e);
			} catch (ParseException e) {
				res.setResponseCode(ErrorCodes.InvalidDate.getValue());
				res.setResponseMessage(ErrorMessages.InvalidDate.getValue());
				logger.error("Invalid Date of Birth",e);
			}
			}else {
				res.setResponseCode(ErrorCodes.NoRecordsFound.getValue());
				res.setResponseMessage(ErrorMessages.NoRecordsFound.getValue());
			}
			return res;
		}
		return res;
	}
	private void addingDefaultParms(InsuranceCardDetails insuranceCardDetails) {
		insuranceCardDetails.setCardStatus("ACTIVE");
		Calendar cal=Calendar.getInstance();
		cal.add(Calendar.DATE,7);
		insuranceCardDetails.setActivateDate(cal.getTime());
		UUID uuid = UUID.randomUUID();
        String uuidAsString = uuid.toString();
		insuranceCardDetails.setCardId(uuidAsString);
}
	@Override
	public Response<InsuranceCardDetails> updateInsureCardDetails(
			UpdateInsuranceCardDetailsRequest insuranceCardDetailsRequest) {

		Response<InsuranceCardDetails> res=new Response<>();
		if(ObjectUtils.isEmpty(insuranceCardDetailsRequest)) {
			res.setResponseCode(ErrorCodes.EmptyRequest.getValue());
			res.setResponseMessage(ErrorMessages.EmptyRequest.getValue());
		}else {
			Optional<EmployeeDetails> employeeDetails=employeeDetailsRepository.findById(insuranceCardDetailsRequest.getEmployeeId());
			if(employeeDetails.isPresent()) {
			Optional<InsuranceCardDetails> insuranceCardDetails=insuranceCardDetailsRepository.findById(insuranceCardDetailsRequest.getCardId());
			try {
				if(insuranceCardDetails.isPresent()) {
				CommonUtils.copyProperties(insuranceCardDetailsRequest, insuranceCardDetails.get());
				if(!CommonUtils.isEmpty(insuranceCardDetailsRequest.getAdditionalDetails())) {
					EmployeeAdditionalDetails employeeAdditionalDetails=insuranceCardDetailsRequest.getAdditionalDetails();
					String additionalDetails=gson.toJson(employeeAdditionalDetails);
					insuranceCardDetails.get().setAdditionalDetails(additionalDetails);
				}
				if(!CommonUtils.isEmpty(insuranceCardDetailsRequest.getDob())) {
					SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
					Date dob= formatter.parse(insuranceCardDetailsRequest.getDob());
					insuranceCardDetails.get().setDob(dob);
				}
				insuranceCardDetailsRepository.save(insuranceCardDetails.get());
				//jmsTemplate.convertAndSend("createEmployee",insuranceCardDetails);
				res.setJsonObject(insuranceCardDetails.get());
				res.setResponseCode(ErrorCodes.SUCCESS.getValue());
				res.setResponseMessage(ErrorMessages.SUCCESS.getValue());
				}else {
					res.setResponseCode(ErrorCodes.NoRecordsFound.getValue());
					res.setResponseMessage(ErrorMessages.NoRecordsFound.getValue());
				}
			}catch(BeansException e) {
				res.setResponseCode(ErrorCodes.BeanException.getValue());
				res.setResponseMessage(ErrorMessages.BeanException.getValue());
				logger.error("Unable to copy request to employee details",e);
			}catch(HibernateException e) {
				res.setResponseCode(ErrorCodes.HibernateException.getValue());
				res.setResponseMessage(ErrorMessages.HibernateException.getValue());
				logger.error("Unable to save employee details",e);
			} catch (ParseException e) {
				res.setResponseCode(ErrorCodes.InvalidDate.getValue());
				res.setResponseMessage(ErrorMessages.InvalidDate.getValue());
				logger.error("Invalid Date of Birth",e);
			}
			}else {
				res.setResponseCode(ErrorCodes.NoRecordsFound.getValue());
				res.setResponseMessage(ErrorMessages.NoRecordsFound.getValue());
			}
			return res;
		}
		return res;
	
	}
	/**
	 * @return the insuranceCardDetailsRepository
	 */
	public IInsuranceCardDetailsRepository getInsuranceCardDetailsRepository() {
		return insuranceCardDetailsRepository;
	}

	/**
	 * @param insuranceCardDetailsRepository the insuranceCardDetailsRepository to set
	 */
	public void setInsuranceCardDetailsRepository(IInsuranceCardDetailsRepository insuranceCardDetailsRepository) {
		this.insuranceCardDetailsRepository = insuranceCardDetailsRepository;
	}

}
