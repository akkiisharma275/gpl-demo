package com.viratrigger.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.viratrigger.api.dto.ErrorLog;

@Repository
public interface ErrorLogRepository  extends JpaRepository<ErrorLog, Long>{
	 List<ErrorLog> findByMobileNumber(long mobileNumber);
	    List<ErrorLog> findByMobileNumberAndLabourId(long mobileNumber, int labourId);
	}