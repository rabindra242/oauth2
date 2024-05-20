package com.example.oauth2backend.excel1.validation;

import com.example.oauth2backend.excel1.Customers;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;


@Slf4j
public class UploadExcelToDB{
    public static String TYPE = "application/vnd.ms-excel";

    public static List<Customers> excelToDB(InputStream is) {
        try {
            Workbook workbook = WorkbookFactory.create(is);
            Sheet sheet = workbook.getSheetAt(0);

            Iterator<Row> rows = sheet.iterator();
            ArrayList<Customers> employees = new ArrayList<>();

            int rowNumber = 0;
            while (rows.hasNext()) {
                Row currentRow = rows.next();
                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }

                Iterator<Cell> cellIterator = currentRow.cellIterator();
                Customers employee = new Customers();
                int cellId = 0;
                while (cellIterator.hasNext()) {
                    Cell cell = currentRow.getCell(cellId, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
                    switch (cellId) {
                        case 0:
                            if (cell.getCellType() == CellType.NUMERIC) {
                                employee.setId((int) cell.getNumericCellValue());
                            } else {
                                throw new IllegalArgumentException("Invalid data type for ID at row " + (rowNumber + 1));
                            }
                            break;
                        case 1:
                            if (cell.getCellType() == CellType.STRING) {
                                employee.setName(cell.getStringCellValue());
                            } else {
                                throw new IllegalArgumentException("Invalid data type for Name at row " + (rowNumber + 1));
                            }
                            break;
                        case 2:
                            if (cell.getCellType() == CellType.STRING) {
                                employee.setEmail(cell.getStringCellValue());
                            } else {
                                throw new IllegalArgumentException("Email Error" + (rowNumber + 1));
                            }
                            break;
                        case 3:
                            if (cell.getCellType() == CellType.STRING) {
                                employee.setGender(cell.getStringCellValue());
                            } else {
                                throw new IllegalArgumentException("Gender Error" + (rowNumber + 1));
                            }
                            break;
                        case 4:
                            if (cell.getCellType() == CellType.STRING) {
                                employee.setContactNo(cell.getStringCellValue());
                            } else {
                                throw new IllegalArgumentException("Contact no error" + (rowNumber + 1));
                            }
                            break;
                        case 5:
                            if (cell.getCellType() == CellType.STRING) {
                                employee.setCountry(cell.getStringCellValue());
                            } else {
                                throw new IllegalArgumentException("Country " + (rowNumber + 1));
                            }
                            break;

                        default:
                            break;
                    }
                    cellId++;
                }
                employees.add(employee);
                rowNumber++;
            }
            workbook.close();
            return employees;
        } catch (IOException e) {
            throw new RuntimeException("Failed to parse Excel file: " + e.getMessage());
        }
    }

}

