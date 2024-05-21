package com.example.oauth2backend.excel1.validation;

import com.example.oauth2backend.excel1.Customers;
import com.example.oauth2backend.excel1.CustomersRepo;
import com.example.oauth2backend.excel1.validation.exception.ExcelFieldValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import static com.example.oauth2backend.excel1.validation.UploadExcelToDB.excelToDB;


@Service
@RequiredArgsConstructor
public class ExcelUploadService {
    private final CustomersRepo customersRepo;

    public List<Customers> save(MultipartFile file) {
        try {
            List<Customers> customersList= excelToDB(file.getInputStream());
            customersRepo.saveAll(customersList);
            return customersList;
        }catch (ExcelFieldValidation | IOException e){
           throw new ExcelFieldValidation(e.getMessage());
        }
    }
}
