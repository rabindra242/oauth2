package com.example.oauth2backend.excel1.validation;

import com.example.oauth2backend.excel1.Customers;
import com.example.oauth2backend.excel1.validation.exception.UserIdNotFoundException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class UploadExcelToDB {
    public static String TYPE_XLSX = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    public static String TYPE_XLS = "application/vnd.ms-excel";

    static String[] headers = {"id", "firstName", "lastName", "email", "gender", "contactNo", "country", "dob"};
    public static String SHEET = "customers";

    public static List<Customers> excelToDb(MultipartFile file) throws IOException {
        String contentType = file.getContentType();
        InputStream inputStream = file.getInputStream();
        Workbook workbook = null;

        try {
            if (TYPE_XLSX.equals(contentType)) {
                workbook = new XSSFWorkbook(inputStream);
            } else if (TYPE_XLS.equals(contentType)) {
                workbook = new HSSFWorkbook(new POIFSFileSystem(inputStream));
            } else {
                throw new IllegalArgumentException("The provided file format is not supported");
            }

            Sheet sheet = workbook.getSheet(SHEET);
            Iterator<Row> rows = sheet.iterator();
            List<Customers> customersList = new ArrayList<>();
            int rowNum = 0;

            while (rows.hasNext()) {
                Row currentRow = rows.next();
                if (rowNum == 0) {
                    rowNum++;
                    continue;
                }

                Iterator<Cell> cellsInRow = currentRow.cellIterator();
                Customers customers = new Customers();
                int cellIdx = 0;

                while (cellsInRow.hasNext()) {
                    Cell currentCell = cellsInRow.next();
                    switch (cellIdx) {
                        case 0:
                            customers.setId((int) currentCell.getNumericCellValue());
                            if (customers.getId() == null) {
                                throw new UserIdNotFoundException("User id not found" + cellsInRow);
                            }
                            break;
                        case 1:
                            customers.setFirstName(currentCell.getStringCellValue());
                            if (customers.getFirstName() == null) {
                                throw new UserIdNotFoundException("User Name not found" + cellsInRow);
                            }
                            break;
                        case 2:
                            customers.setLastName(currentCell.getStringCellValue());
                            if (customers.getLastName() == null) {
                                throw new UserIdNotFoundException("User LastName not found" + cellsInRow);
                            }
                            break;
                        case 3:
                            customers.setEmail(currentCell.getStringCellValue());
                            if (customers.getEmail() == null) {
                                throw new UserIdNotFoundException("User Email not found" + cellsInRow);
                            }
                            break;
                        case 4:
                            customers.setGender(currentCell.getStringCellValue());
                            if (customers.getGender() == null) {
                                throw new UserIdNotFoundException("User Gender not found" + cellsInRow);
                            }
                            break;
                        case 5:
                            customers.setContactNo(String.valueOf(currentCell.getNumericCellValue()));
                            if (customers.getContactNo() == null) {
                                throw new UserIdNotFoundException("User Contact not found" + cellsInRow);
                            }
                            break;
                        case 6:
                            customers.setCountry(currentCell.getStringCellValue());
                            if (customers.getCountry() == null) {
                                throw new UserIdNotFoundException("User Country not found" + cellsInRow);
                            }
                            break;
                        default:
                            break;
                    }
                    cellIdx++;
                }
                customersList.add(customers);
                rowNum++;
            }

            return customersList;

        } finally {
            if (workbook != null) {
                workbook.close();
            }
            inputStream.close();
        }
    }
}
