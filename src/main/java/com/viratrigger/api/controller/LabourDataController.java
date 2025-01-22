package com.viratrigger.api.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.viratrigger.api.dto.LabourData;
import com.viratrigger.api.dto.LabourDataRequest;
import com.viratrigger.api.service.ApiCallService;

@RestController
@RequestMapping("/api")
public class LabourDataController {
	@Autowired
	private ApiCallService apiCallService; // Injecting the ApiCallService

	private volatile boolean stopScript = false; // Flag to stop the script
	private volatile boolean isScriptRunning = false; // Flag to track if the script is running

	@PostMapping("/process")
	public ResponseEntity<String> processLabourData(@RequestBody LabourDataRequest request) {

		isScriptRunning = true; // Mark the script as running
		stopScript = false; // Reset stop flag
		try {
			// Validate input data
			if (request.getData() == null || request.getData().isEmpty()) {
				return ResponseEntity.badRequest().body("No data provided.");
			}

			List<LabourData> errorList = new ArrayList<>(); // List to track failed records
			int maxRetries = 5;
			int retryCount = 0;

			// Initial processing
			for (LabourData labourData : request.getData()) {

				// Check if the script was stopped

				if (stopScript) {
					System.out.println("Processing stopped by user.");
					isScriptRunning = false;
					return ResponseEntity.status(200).body("Script stopped successfully.");
				}

				boolean isSuccess = apiCallService.makeApiCall(
						String.valueOf(labourData.getMobileNumber()),
						String.valueOf(labourData.getLabourId())
						);

				if (!isSuccess) {
					errorList.add(labourData); // Add to error list if failed
				}

				// Pause for 5 seconds
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
					System.err.println("Thread interrupted: " + e.getMessage());
				}
			}


			// Retry logic
			while (!errorList.isEmpty() && retryCount < maxRetries) {
				retryCount++;
				System.out.println("Retry attempt: " + retryCount);

				List<LabourData> retryList = new ArrayList<>(errorList); // Copy current errors
				errorList.clear(); // Clear for the next iteration

				for (LabourData labourData : retryList) {

					// Check if the script was stopped
					if (stopScript) {
						System.out.println("Processing stopped by user.");
						isScriptRunning = false;
						return ResponseEntity.status(200).body("Script stopped successfully.");
					}

					boolean isSuccess = apiCallService.makeApiCall(
							String.valueOf(labourData.getMobileNumber()),
							String.valueOf(labourData.getLabourId())
							);

					if (!isSuccess) {
						errorList.add(labourData); // Add back to error list if retry fails
					}
				}
			}

			// Check if there are still errors after retries
			if (!errorList.isEmpty()) {
				StringBuilder errorResponse = new StringBuilder("Some records failed to process after " + maxRetries + " retries:\n");
				for (LabourData labourData : errorList) {
					errorResponse.append("Labour ID: ").append(labourData.getLabourId())
					.append(", Mobile Number: ").append(labourData.getMobileNumber()).append("\n");
				}

				isScriptRunning = false;
				return ResponseEntity.status(500).body(errorResponse.toString());
			}

			System.out.println("All records processed successfully.");
			return ResponseEntity.ok("All records processed successfully.");
		} catch (Exception e) {
			System.err.println("Error processing labour data: " + e.getMessage());

			isScriptRunning = false;

			return ResponseEntity.status(500).body("Error processing labour data: " + e.getMessage());
		}
	}

	@PostMapping("/stopScript")
	public ResponseEntity<String> stopScript() {
	    if (!isScriptRunning) {
	        System.out.println("No script is running. Sending 400 response.");
	        return ResponseEntity.status(400).body("No script is currently running.");
	    }

	    stopScript = true;
	    return ResponseEntity.ok("Script stopping initiated.");
	}
}