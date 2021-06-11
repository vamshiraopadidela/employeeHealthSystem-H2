package com.prokarma.rest.service.Implementation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import org.hibernate.HibernateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import com.google.gson.Gson;
import com.prokarama.beans.CreateEmployeeRequest;
import com.prokarama.beans.EmployeeAdditionalDetails;
import com.prokarama.beans.Response;
import com.prokarama.beans.UpdateEmployeeRequest;
import com.prokarma.common.CommonUtils;
import com.prokarma.common.SequenceGenerator;
import com.prokarma.entity.EmployeeDetails;
import com.prokarma.entity.InsuranceCardDetails;
import com.prokarma.errorcodes.ErrorCodes;
import com.prokarma.errorcodes.ErrorMessages;
import com.prokarma.repositories.IEmployeeDetailsRepository;
import com.prokarma.repositories.IInsuranceCardDetailsRepository;
import com.prokarma.rest.service.IEmployeeDetailsService;

@Component
public class EmployeeDetailsService implements IEmployeeDetailsService{
	private static final Logger logger = LoggerFactory.getLogger(EmployeeDetailsService.class);

	@Autowired
	private IEmployeeDetailsRepository employeeDetailsRepository;
	
	@Autowired
	private IInsuranceCardDetailsRepository insuranceCardDetailsRepository;
	
	@Autowired
	private JmsTemplate jmsTemplate;
		
	@Autowired
	private Gson gson;
	
    @Transactional
	@Override
	public Response<EmployeeDetails> addEmployeeDetails(CreateEmployeeRequest createEmployeeRequest) {
		Response<EmployeeDetails> res=new Response<>();
		if(ObjectUtils.isEmpty(createEmployeeRequest)) {
			res.setResponseCode(ErrorCodes.EmptyRequest.getValue());
			res.setResponseMessage(ErrorMessages.EmptyRequest.getValue());
		}else {
			EmployeeDetails employeeDetails=new EmployeeDetails();
			try {
				BeanUtils.copyProperties(createEmployeeRequest, employeeDetails);
				logger.info("Copying of EmployeeDetails from Request processed");
				addingDefaultParms(employeeDetails);
				EmployeeAdditionalDetails employeeAdditionalDetails=createEmployeeRequest.getAdditionalDetails();
				String additionalDetails=gson.toJson(employeeAdditionalDetails);
				employeeDetails.setAddtionalDetails(additionalDetails);
				SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
				Date dob= formatter.parse(createEmployeeRequest.getDob());
				employeeDetails.setDob(dob);
				employeeDetailsRepository.save(employeeDetails);
				jmsTemplate.convertAndSend("createEmployee",employeeDetails);
				res.setJsonObject(employeeDetails);
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
			return res;
		}
		return res;
	}

	private void addingDefaultParms(EmployeeDetails employeeDetails) {
			employeeDetails.setEmployeeStatus("ACTIVE");
			employeeDetails.setEmployeeId(SequenceGenerator.generateEmployeeIdSequence());
			employeeDetails.setDateOfJoining(Calendar.getInstance().getTime());
	}
    @Transactional
	@Override
	public Response<EmployeeDetails> updateEmployeeDetails(UpdateEmployeeRequest updateEmployeeRequest) {
		Response<EmployeeDetails> res=new Response<>();
		if(ObjectUtils.isEmpty(updateEmployeeRequest)) {
			res.setResponseCode(ErrorCodes.EmptyRequest.getValue());
			res.setResponseMessage(ErrorMessages.EmptyRequest.getValue());
		}else {
			try {
				Optional<EmployeeDetails> employeeDetails=employeeDetailsRepository.findById(updateEmployeeRequest.getEmployeeId());
				if(employeeDetails.isPresent()) {
					CommonUtils.copyProperties(updateEmployeeRequest, employeeDetails.get());
					if(!CommonUtils.isEmpty(updateEmployeeRequest.getAdditionalDetails())) {
						EmployeeAdditionalDetails employeeAdditionalDetails=updateEmployeeRequest.getAdditionalDetails();
						String additionalDetails=gson.toJson(employeeAdditionalDetails);
						employeeDetails.get().setAddtionalDetails(additionalDetails);
					}
					if(!CommonUtils.isEmpty(updateEmployeeRequest.getDob())) {
						SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
						Date dob= formatter.parse(updateEmployeeRequest.getDob());
						employeeDetails.get().setDob(dob);
					}
					employeeDetailsRepository.save(employeeDetails.get());
					res.setJsonObject(employeeDetails.get());
					res.setResponseCode(ErrorCodes.SUCCESS.getValue());
					res.setResponseMessage(ErrorMessages.SUCCESS.getValue());
				}else {
					res.setResponseCode(ErrorCodes.NoRecordsFound.getValue());
					res.setResponseMessage(ErrorMessages.NoRecordsFound.getValue());
				}	}catch(BeansException e) {
					res.setResponseCode(ErrorCodes.BeanException.getValue());
					res.setResponseMessage(ErrorMessages.BeanException.getValue());
					logger.error("Unable to copy request to employee details",e);
				}catch(HibernateException e) {
					res.setResponseCode(ErrorCodes.HibernateException.getValue());
					res.setResponseMessage(ErrorMessages.HibernateException.getValue());
					logger.error("Unable to save employee details",e);
				}catch (ParseException e) {
					res.setResponseCode(ErrorCodes.InvalidDate.getValue());
					res.setResponseMessage(ErrorMessages.InvalidDate.getValue());
					logger.error("Invalid Date of Birth",e);
				}

		}
		return res;
	}
	

	@Override
	public Response<EmployeeDetails> getEmployeeDetails(String employeeId) {
		Response<EmployeeDetails> res=new Response<>();
		if(ObjectUtils.isEmpty(employeeId)) {
			res.setResponseCode(ErrorCodes.EmptyRequest.getValue());
			res.setResponseMessage(ErrorMessages.EmptyRequest.getValue());
		}else {
			Optional<EmployeeDetails> employeeDetails=employeeDetailsRepository.findById(employeeId);
			if(employeeDetails.isPresent()) {
				res.setJsonObject(employeeDetails.get());
				res.setResponseCode(ErrorCodes.SUCCESS.getValue());
				res.setResponseMessage(ErrorMessages.SUCCESS.getValue());
			}else {
				res.setResponseCode(ErrorCodes.NoRecordsFound.getValue());
				res.setResponseMessage(ErrorMessages.NoRecordsFound.getValue());
			}
		}
		return res;
	}

	@Override
	public Response<List<EmployeeDetails>> getAllEmployeeDetails() {
		List<EmployeeDetails> employeeDetailsList=employeeDetailsRepository.findAll();
		Response<List<EmployeeDetails>> res=new Response<>();
		if(employeeDetailsList!=null&&employeeDetailsList.isEmpty()) {
			res.setResponseCode(ErrorCodes.NoRecordsFound.getValue());
			res.setResponseMessage(ErrorMessages.NoRecordsFound.getValue());
		}else {
			res.setJsonObject(employeeDetailsList);
			res.setResponseCode(ErrorCodes.SUCCESS.getValue());
			res.setResponseMessage(ErrorMessages.SUCCESS.getValue());
		}
		return res;
	}
    @Transactional
	@Override
	public Response<EmployeeDetails> deactiveEmployeeDetails(String employeeId) {

		Response<EmployeeDetails> res=new Response<>();
		if(ObjectUtils.isEmpty(employeeId)) {
			res.setResponseCode(ErrorCodes.EmptyRequest.getValue());
			res.setResponseMessage(ErrorMessages.EmptyRequest.getValue());
		}else {
			Optional<EmployeeDetails> employeeDetails=employeeDetailsRepository.findById(employeeId);
			if(employeeDetails.isPresent()) {
				List<InsuranceCardDetails> insuranceCardDetails=insuranceCardDetailsRepository.findByEmployeeDetails(employeeDetails.get());
				Calendar cal=Calendar.getInstance();
				cal.add(Calendar.DATE,7);
				List<InsuranceCardDetails> insuranceCardDetailsUpdated=null;
				if(insuranceCardDetails!=null&&!insuranceCardDetails.isEmpty()) {
					insuranceCardDetailsUpdated=insuranceCardDetails.stream().
							map(x->{x.setCardStatus("INACTIVE");x.setExpirationDate(cal.getTime());return x;}).collect(Collectors.toList());
					insuranceCardDetailsRepository.saveAll(insuranceCardDetailsUpdated);
				}
			    employeeDetails.get().setDateOfLeaving(Calendar.getInstance().getTime());
				employeeDetails.get().setEmployeeStatus("INACTIVE");
				employeeDetailsRepository.save(employeeDetails.get());
				if(insuranceCardDetailsUpdated!=null&&!insuranceCardDetailsUpdated.isEmpty()) {
					for(InsuranceCardDetails insuranceCardDetailsTemp:insuranceCardDetailsUpdated) {
						jmsTemplate.convertAndSend("deleteEmployee",insuranceCardDetailsTemp);
					}
				}
				res.setJsonObject(employeeDetails.get());
				res.setResponseCode(ErrorCodes.SUCCESS.getValue());
				res.setResponseMessage(ErrorMessages.SUCCESS.getValue());
			}else {
				res.setResponseCode(ErrorCodes.NoRecordsFound.getValue());
				res.setResponseMessage(ErrorMessages.NoRecordsFound.getValue());
			}
		}
		return res;
	
	
	}

	/**
	 * @return the employeeDetailsRepository
	 */
	public IEmployeeDetailsRepository getEmployeeDetailsRepository() {
		return employeeDetailsRepository;
	}

	/**
	 * @param employeeDetailsRepository the employeeDetailsRepository to set
	 */
	public void setEmployeeDetailsRepository(IEmployeeDetailsRepository employeeDetailsRepository) {
		this.employeeDetailsRepository = employeeDetailsRepository;
	}


}
