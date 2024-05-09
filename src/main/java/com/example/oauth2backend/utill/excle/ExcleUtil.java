package com.example.oauth2backend.utill.excle;

import com.example.oauth2backend.dto.FormRequestDto;
import com.example.oauth2backend.dto.FormResponseDTO;
import com.example.oauth2backend.entity.FormDataEntity;
import com.example.oauth2backend.repo.FormRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class ExcleUtil implements DbToExcleService {
    private final FormRepo formRepo;
    private final ModelMapper modelMapper;
    @Override
    public ByteArrayInputStream export(List<FormResponseDTO> dataEntities) {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("FormDataEntries");
            Row row = sheet.createRow(0);
            Cell cell = row.createCell(0);
            cell.setCellValue("Id");
            cell = row.createCell(1);
            cell.setCellValue("phoneNumber");
            cell = row.createCell(2);
            cell.setCellValue("dateOfBirth");
            cell = row.createCell(3);
            cell.setCellValue("jobType");
            cell = row.createCell(4);
            cell.setCellValue("gender");
            for (int i = 0; i < dataEntities.size(); i++) {
                Row dataRow = sheet.createRow(i + 1);
                dataRow.createCell(0).setCellValue(dataEntities.get(i).getId());
                dataRow.createCell(1).setCellValue(dataEntities.get(i).getPhoneNumber());
                dataRow.createCell(2).setCellValue(dataEntities.get(i).getDateOfBirth());
                dataRow.createCell(3).setCellValue(dataEntities.get(i).getJobType());
                dataRow.createCell(4).setCellValue(dataEntities.get(i).getGender());
            }
            for (int i = 0; i < 4; i++) {
                sheet.autoSizeColumn(i);
            }

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            workbook.write(outputStream);
            return new ByteArrayInputStream(outputStream.toByteArray());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<FormResponseDTO> getAllFormData() {
        List<FormDataEntity> listOfData=formRepo.findAll();
        return listOfData.stream()
                .map(e->modelMapper.map(e,FormResponseDTO.class))
                .collect(Collectors.toList());
    }
}
