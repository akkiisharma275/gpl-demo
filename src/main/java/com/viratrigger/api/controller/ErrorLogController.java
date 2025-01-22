package com.viratrigger.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.viratrigger.api.dto.ErrorLog;
import com.viratrigger.api.service.ErrorLogService;

@RestController
@RequestMapping("/api")
public class ErrorLogController {
	
	@Autowired
    private ErrorLogService errorLogService;

    @GetMapping("/error-log")
    public ResponseEntity<?> getErrorLog(
            @RequestParam("mobileNumber") long mobileNumber,
            @RequestParam(value = "labourId", required = false) Integer labourId) {

        try {
            // Fetch logs from the service
            List<ErrorLog> logs = errorLogService.getErrorLogs(mobileNumber, labourId);

            // If no logs are found
            if (logs.isEmpty()) {
                return ResponseEntity.status(404)
                        .body("No error logs found for MobileNumber: " + mobileNumber
                                + (labourId != null ? " and LabourId: " + labourId : ""));
            }

            // Return logs if found
            return ResponseEntity.ok(logs);

        } catch (Exception e) {
            // Handle unexpected errors
            return ResponseEntity.status(500).body("Error retrieving logs: " + e.getMessage());
        }
    }
}
	


