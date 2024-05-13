package com.example.oauth2backend.excel;

import com.example.oauth2backend.entity.FormDataEntity;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FormatCheckerHelper {

    public static boolean checkContentType(MultipartFile file){
        var contentType=file.getContentType();
        if(contentType.equals("application/vnd.ms-excel")){
            return true;
        }else {
            return false;
        }
    }

    public static List<DataItemEntity> convertExcleToList(InputStream s) {
        List<DataItemEntity> list = new ArrayList<>();
        try {
            XSSFWorkbook workbook = new XSSFWorkbook(s);
            XSSFSheet sheet = workbook.getSheet("FormDataEntries");
            int rowNumber = 0;
            Iterator<Row> rowIterator = sheet.iterator();
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                if (rowNumber == 0) {
                    rowNumber++;
                    continue; // Skip header row
                }

                Iterator<Cell> cellIterator = row.iterator();
                int cellNumber = 0;
                DataItemEntity formDataEntity = new DataItemEntity();
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    switch (cellNumber) {
                        case 0:
                            if (cell.getCellType() == CellType.NUMERIC) {
                                // Handle numeric value
                                formDataEntity.setId((int)cell.getNumericCellValue());
                            } else {
                                formDataEntity.setId(Integer.valueOf(cell.getStringCellValue()));
                            }
                            break;
                        case 1:
                            formDataEntity.setPhoneNumber(cell.getStringCellValue());
                            break;
                        case 2:
                            String dobString;
                            if (cell.getCellType() == CellType.NUMERIC) {
                                // Handle numeric value
                                dobString = String.valueOf(cell.getNumericCellValue());
                            } else {
                                dobString = cell.getStringCellValue();
                            }
                            LocalDate dob = LocalDate.parse(dobString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                            formDataEntity.setDateOfBirth(dob);
                            break;
                        case 3:
                            formDataEntity.setJobType(cell.getStringCellValue());
                            break;
                        case 4:
                            formDataEntity.setGender(cell.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                    cellNumber++;
                }
                list.add(formDataEntity);
            }
        } catch (IOException e) {
            e.printStackTrace(); // Handle or log the exception
        }
        return list;
    }



}
