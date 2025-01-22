package com.viratrigger.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.viratrigger.api.ResponsDetails;
import com.viratrigger.api.constant.RestConstant;
import com.viratrigger.api.repository.ExcelRecordRepository;
import com.viratrigger.api.service.ExcelService;

@RestController
@RequestMapping("/api")
public class ExcelController {

    @Autowired
    private ExcelService excelService;

    @Autowired
    private ExcelRecordRepository excelRecordRepository;

    @PostMapping("/uploadExcel")
    public ResponsDetails uploadExcel(
            @RequestParam("file") MultipartFile file) {
        ResponsDetails response = new ResponsDetails();
        try {
            var data = excelService.processExcelFile(file);
            response.setCode(RestConstant.SUCCESS_CODE);
            response.setMessage(RestConstant.SUCCESS_MESSAGE);
            response.setData(data);
        } catch (Exception e) {
            response.setCode(RestConstant.FAILURE_CODE);
            response.setMessage(RestConstant.FAILURE_MESSAGE);
            response.setMessage1(e.getMessage());
        }
        return response;
    }
    ///api/getProcessedRecords

    @GetMapping("/getProcessedRecords")
    public ResponsDetails getProcessedRecords() {
        ResponsDetails response = new ResponsDetails();
        try {
            var records = excelRecordRepository.findAll();
            response.setCode(RestConstant.SUCCESS_CODE);
            response.setMessage(RestConstant.SUCCESS_MESSAGE);
            response.setData(records);
        } catch (Exception e) {
            response.setCode(RestConstant.FAILURE_CODE);
            response.setMessage(RestConstant.FAILURE_MESSAGE);
            response.setMessage1(e.getMessage());
        }
        return response;
    }
}