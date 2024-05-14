package com.example.oauth2backend.excel1;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcleHelper {

    public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";

    static String[] headers={"id","firstName","lastName","email","gender","contactNo","country","dob"};
    public static String SHEET="customers";


    public static boolean hasExcleFormat(MultipartFile file){if(!TYPE.equals(file.getContentType())){
            return false;
        }
        return true;
    }

    public static List<Customers> excelToTutorials(InputStream inputStream){

        try {
            Workbook workbook=new XSSFWorkbook(inputStream);
            Sheet sheet=workbook.getSheet(SHEET);
            Iterator<Row> rows=sheet.iterator();
            List<Customers> customersList=new ArrayList<>();
            int rowNum=0;
            while(rows.hasNext()){
                Row currentRow=rows.next();
                if (rowNum==0){
                    rowNum++;
                    continue;
                }

                Iterator<Cell> cellsInRows=currentRow.cellIterator();
                Customers customers = new Customers();
                int cellIdx=0;
                while (cellsInRows.hasNext()) {
                    Cell currentCell=cellsInRows.next();
                    switch (cellIdx){
                        case 0:
                            customers.setId((int)currentCell.getNumericCellValue());
                            break;
                        case 1:
                            customers.setFirstName(currentCell.getStringCellValue());
                            break;
                        case 2:
                            customers.setLastName(currentCell.getStringCellValue());
                            break;
                        case 3:
                            customers.setEmail(currentCell.getStringCellValue());
                            break;
                        case 4:
                            customers.setGender(currentCell.getStringCellValue());
                            break;
                        case 5:
                            customers.setContactNo(currentCell.getStringCellValue());
                            break;
                        case 6:
                            customers.setCountry(currentCell.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                    cellIdx++;
                }
                customersList.add(customers);

            }
            workbook.close();
            return customersList;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
