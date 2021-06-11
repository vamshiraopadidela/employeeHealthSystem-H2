package com.prokarma.rest.service;

import org.springframework.stereotype.Service;

import com.prokarama.beans.CreateInsuranceCardDetailsRequest;
import com.prokarama.beans.Response;
import com.prokarama.beans.UpdateInsuranceCardDetailsRequest;
import com.prokarma.entity.InsuranceCardDetails;
@Service
public interface IInsuranceCardDetailsService {
	public Response<InsuranceCardDetails> addInsureCardDetails(CreateInsuranceCardDetailsRequest insuranceCardDetailsRequest);

	public Response<InsuranceCardDetails> updateInsureCardDetails(UpdateInsuranceCardDetailsRequest insuranceCardDetailsRequest);

}
