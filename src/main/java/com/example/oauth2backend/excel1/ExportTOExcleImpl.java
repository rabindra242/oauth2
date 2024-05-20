package com.example.oauth2backend.excel1;

import com.example.oauth2backend.dto.FormResponseDTO;
import com.example.oauth2backend.entity.FormDataEntity;
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
import java.util.stream.Collectors;
@Service
@RequiredArgsConstructor
public class ExportTOExcleImpl implements ExportToExcel{
    private final CustomersRepo customersRepo;
    private final ModelMapper modelMapper;
    @Override
    public ByteArrayInputStream exportUserDb(List<CustomerRequestDto> customerRequestDtos) {
        try (Workbook workbook=new XSSFWorkbook()){
            Sheet sheet=workbook.createSheet("customers");
            Row row=sheet.createRow(0);

            Cell cell=row.createCell(0);
            cell.setCellValue("Id");
            cell=row.createCell(1);
            cell.setCellValue("FirstName");
            cell=row.createCell(2);
            cell.setCellValue("lastName");
            cell=row.createCell(3);
            cell.setCellValue("email");
            cell=row.createCell(4);
            cell.setCellValue("gender");
            cell=row.createCell(5);
            cell.setCellValue("contactNo");
            cell=row.createCell(6);
            cell.setCellValue("country");
            cell=row.createCell(7);
            cell.setCellValue("dob");
            for (int i=0;i<customerRequestDtos.size();i++){
                Row dataRow=sheet.createRow(i+1);
                dataRow.createCell(0).setCellValue(customerRequestDtos.get(i).getId());
                dataRow.createCell(1).setCellValue(customerRequestDtos.get(i).getName());
                dataRow.createCell(2).setCellValue(customerRequestDtos.get(i).getEmail());
                dataRow.createCell(3).setCellValue(customerRequestDtos.get(i).getGender());
                dataRow.createCell(4).setCellValue(customerRequestDtos.get(i).getContactNo());
                dataRow.createCell(5).setCellValue(customerRequestDtos.get(i).getCountry());
            }
            for (int i=0;i<customerRequestDtos.size();i++){
                sheet.autoSizeColumn(i);
            }
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            workbook.write(outputStream);
            return new ByteArrayInputStream(outputStream.toByteArray());


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



    public List<CustomerRequestDto> getAllFormData() {
        List<Customers> listOfData=customersRepo.findAll();
        return listOfData.stream()
                .map(e->modelMapper.map(e,CustomerRequestDto.class))
                .collect(Collectors.toList());
    }
}
