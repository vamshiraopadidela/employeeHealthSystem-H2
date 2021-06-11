package com.prokarma.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prokarma.entity.EmailQueue;
@Repository
public interface IEmailQueueRepository extends JpaRepository<EmailQueue, String> {

}
