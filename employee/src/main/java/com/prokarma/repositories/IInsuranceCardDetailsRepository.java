package com.prokarma.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.prokarma.entity.EmployeeDetails;
import com.prokarma.entity.InsuranceCardDetails;

@Repository
public interface IInsuranceCardDetailsRepository extends JpaRepository<InsuranceCardDetails,String>{

	public List<InsuranceCardDetails> findByEmployeeDetails(EmployeeDetails employeeDetails);
	
	 @Query("FROM InsuranceCardDetails b where b.cardStatus=:cardStatus and b.expirationDate<:expirationDate")
	public List<InsuranceCardDetails> getExpiredInsuranceRecords(@Param("expirationDate")Date currentTime,@Param("cardStatus") String cardStatus);
}
