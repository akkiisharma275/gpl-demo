package com.viratrigger.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.viratrigger.api.dto.ErrorLog;
import com.viratrigger.api.repository.ErrorLogRepository;

@Service
public class ErrorLogService {
	@Autowired
    private ErrorLogRepository errorLogRepository;

    public List<ErrorLog> getErrorLogs(long mobileNumber, Integer labourId) {
        try {
            // Fetch based on parameters
            if (labourId != null) {
                return errorLogRepository.findByMobileNumberAndLabourId(mobileNumber, labourId);
            }
            return errorLogRepository.findByMobileNumber(mobileNumber);
        } catch (DataAccessException e) {
            throw new RuntimeException("Database error occurred: " + e.getMessage(), e);
        }
    }
}

