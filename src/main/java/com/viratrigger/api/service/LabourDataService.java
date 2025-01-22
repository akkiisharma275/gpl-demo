package com.viratrigger.api.service;

import org.springframework.stereotype.Service;

import com.viratrigger.api.dto.LabourDataRequest;

@Service
public class LabourDataService {
	
    

	public void processLabourData(LabourDataRequest request) {
		// TODO Auto-generated method stub
		System.out.println(request.getTimeInterval());
		
	}

	
	
}


