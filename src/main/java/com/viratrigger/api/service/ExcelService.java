package com.viratrigger.api.service;

import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.viratrigger.api.dto.ExcelRecord;
import com.viratrigger.api.repository.ExcelRecordRepository;

@Service
public class ExcelService {

    @Autowired
    private ExcelRecordRepository excelRecordRepository;

    public List<Map<String, String>> processExcelFile(MultipartFile file) throws Exception {
        List<Map<String, String>> data = new ArrayList<>();

        try (InputStream inputStream = file.getInputStream();
             Workbook workbook = WorkbookFactory.create(inputStream)) {

            Sheet sheet = workbook.getSheetAt(0);
            Row headerRow = sheet.getRow(0);
            if (headerRow == null) {
                throw new Exception("Excel file must have a header row.");
            }

            List<String> headers = new ArrayList<>();
            headerRow.forEach(cell -> headers.add(cell.toString()));

            for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
                Row row = sheet.getRow(rowIndex);
                if (row == null) continue;

                Map<String, String> rowData = new HashMap<>();
                for (int colIndex = 0; colIndex < headers.size(); colIndex++) {
                    Cell cell = row.getCell(colIndex, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                    rowData.put(headers.get(colIndex), cell.toString());
                }
                data.add(rowData);

                // Save record
                ExcelRecord record = new ExcelRecord();
                record.setUserId(rowData.get("userId"));
                record.setName(rowData.get("name"));
                record.setNumber(rowData.get("number"));
                record.setStatus("Processed");
                record.setMessage("Successfully processed.");
                record.setCreatedAt(LocalDateTime.now());
                excelRecordRepository.save(record);
            }

        } catch (Exception e) {
            throw new Exception("Error processing Excel file.", e);
        }

        return data;
    }

	public Object readExcelData(MultipartFile file) {
		// TODO Auto-generated method stub
		return null;
	}
}