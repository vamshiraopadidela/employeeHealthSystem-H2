package com.prokarma.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prokarma.entity.EmailTemplate;
@Repository
public interface IEmailTemplateRepository extends JpaRepository<EmailTemplate, String>{

	public List<EmailTemplate> findByEmailTemplate(String emailTemplate);
}
