package com.example.oauth2backend.excel1.validation;

import com.example.oauth2backend.excel1.CustomerRequestDto;
import com.example.oauth2backend.excel1.CustomersRepo;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExcalFormat {
    public ByteArrayInputStream formatDownload() throws IOException {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("customers");
            Row row = sheet.createRow(0);
            Cell cell = row.createCell(0);
            cell.setCellValue("Id");
            cell = row.createCell(1);
            cell.setCellValue("FirstName");
            cell = row.createCell(2);
            cell.setCellValue("lastName");
            cell = row.createCell(3);
            cell.setCellValue("email");
            cell = row.createCell(4);
            cell.setCellValue("gender");
            cell = row.createCell(5);
            cell.setCellValue("contactNo");
            cell = row.createCell(6);
            cell.setCellValue("country");
            cell = row.createCell(7);
            cell.setCellValue("dob");
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            workbook.write(outputStream);
            return new ByteArrayInputStream(outputStream.toByteArray());

        }
    }
}