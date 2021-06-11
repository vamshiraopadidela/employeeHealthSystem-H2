package com.prokarma.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prokarma.entity.EmployeeDetails;

@Repository
public interface IEmployeeDetailsRepository extends JpaRepository<EmployeeDetails,String> {

}
