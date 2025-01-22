package com.viratrigger.api.wrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.viratrigger.api.ResponsDetails;
import com.viratrigger.api.service.ExcelService;

@Service
public class ExcelWrapper {
	@Autowired
    private ExcelService excelService;

	public ResponsDetails processExcelFile(String userId, String name, String number, MultipartFile file) {
        ResponsDetails response = new ResponsDetails();
        try {
            var data = excelService.readExcelData(file);

            response.setCode("200");
            response.setMessage("File processed successfully.");
            response.setData(data); // Processed data from Excel
        } catch (Exception e) {
            response.setCode("500");
            response.setMessage("Failed to process the file.");
            response.setMessage1(e.getMessage());
        }
        return response;
    }
}


