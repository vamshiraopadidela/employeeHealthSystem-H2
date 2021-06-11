package com.prokarma.scheduling;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.prokarma.entity.InsuranceCardDetails;
import com.prokarma.repositories.IInsuranceCardDetailsRepository;

@Component
public class InsuranceSchedular {
	@Autowired
	private IInsuranceCardDetailsRepository insuranceCardDetailsRepository;
	@Scheduled(cron = "0 0/1 * * * *")
	   public void schedularInsurance() {
		List<InsuranceCardDetails> insuranceCardDetailsList=insuranceCardDetailsRepository.
				getExpiredInsuranceRecords(Calendar.getInstance().getTime(),"INACTIVE");
		if(insuranceCardDetailsList!=null&&!insuranceCardDetailsList.isEmpty()) {
			insuranceCardDetailsRepository.deleteAll(insuranceCardDetailsList);
		}
	   }
}
