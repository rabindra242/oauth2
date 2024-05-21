package com.example.oauth2backend.excel1.validation;

import com.example.oauth2backend.excel1.Customers;
import com.example.oauth2backend.excel1.validation.exception.BlankCellException;
import com.example.oauth2backend.excel1.validation.exception.ExcelFieldValidation;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.NumberToTextConverter;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class UploadExcelToDB {
    public static String TYPE = "application/vnd.ms-excel";

    public static List<Customers> excelToDB(InputStream is) throws IOException {
        Workbook workbook = null;
        try {
            workbook = WorkbookFactory.create(is);
            Sheet sheet = workbook.getSheetAt(0);
            List<Customers> customers = new ArrayList<>();

            int rowNumber = 0;
            for (Row currentRow : sheet) {
                if (rowNumber == 0) {
                    rowNumber++;
                    continue; // skip header row
                }

                Customers customer = new Customers();
                for (int cellIdx = 0; cellIdx < 6; cellIdx++) {
                    Cell currentCell = currentRow.getCell(cellIdx, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);

//                    if (currentCell == null || currentCell.getCellType() == CellType.BLANK) {
//                        String columnName = getColumnName(cellIdx);
//                        throw new BlankCellException("Blank cell found at row " + (rowNumber + 1) + ", column " + (cellIdx + 1) + " (" + columnName + ")");
//                    }

                    switch (cellIdx) {
                        case 0:
                            if (currentCell.getCellType() == CellType.NUMERIC) {
                                customer.setId((int) currentCell.getNumericCellValue());
                            } else {
                                throw new ExcelFieldValidation("Id not found at row " + (rowNumber + 1) + ", column " + (cellIdx + 1));
                            }
                            break;
                        case 1:
                            if (currentCell.getCellType() == CellType.STRING) {
                                customer.setName(currentCell.getStringCellValue());
                            } else {
                                throw new ExcelFieldValidation("User Name not found at row " + (rowNumber + 1) + ", column " + (cellIdx + 1));
                            }
                            break;
                        case 2:
                            if (currentCell.getCellType() == CellType.STRING) {
                                customer.setEmail(currentCell.getStringCellValue());
                            } else {
                                throw new ExcelFieldValidation("Email not found at row " + (rowNumber + 1) + ", column " + (cellIdx + 1));
                            }
                            break;
                        case 3:
                            if (currentCell.getCellType() == CellType.STRING) {
                                customer.setGender(currentCell.getStringCellValue());
                            } else {
                                throw new ExcelFieldValidation("Gender not found at row " + (rowNumber + 1) + ", column " + (cellIdx + 1));
                            }
                            break;
                        case 4:
                            if (currentCell.getCellType() == CellType.NUMERIC) {
                                customer.setContactNo(NumberToTextConverter.toText(currentCell.getNumericCellValue()));
                            } else if (currentCell.getCellType() == CellType.STRING) {
                                customer.setContactNo(currentCell.getStringCellValue());
                            } else {
                                throw new ExcelFieldValidation("Contact No not found at row " + (rowNumber + 1) + ", column " + (cellIdx + 1));
                            }
                            break;
                        case 5:
                            if (currentCell.getCellType() == CellType.STRING) {
                                customer.setCountry(currentCell.getStringCellValue());
                            } else {
                                throw new ExcelFieldValidation("Country not found at row " + (rowNumber + 1) + ", column " + (cellIdx + 1));
                            }
                            break;
                        default:
                            break;
                    }
                }
                customers.add(customer);
                rowNumber++;
            }
            return customers;
        } catch (ExcelFieldValidation e) {
//            log.error(e.getMessage(), e);
            throw new ExcelFieldValidation("Excel file validation failed: " + e.getMessage());
        } finally {
            if (workbook != null) {
                try {
                    workbook.close();
                } catch (IOException e) {
                    log.error("Error closing the workbook", e);
                }
            }
        }
    }

//    private static String getColumnName(int index) {
//        switch (index) {
//            case 0:
//                return "Id";
//            case 1:
//                return "Name";
//            case 2:
//                return "Email";
//            case 3:
//                return "Gender";
//            case 4:
//                return "Contact No";
//            case 5:
//                return "Country";
//            default:
//                return "Unknown";
//        }
//    }
}
