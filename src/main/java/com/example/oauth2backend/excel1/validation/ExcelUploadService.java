package com.example.oauth2backend.excel1.validation;

import com.example.oauth2backend.excel1.Customers;
import com.example.oauth2backend.excel1.CustomersRepo;
import com.example.oauth2backend.excel1.ExcleHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static com.example.oauth2backend.excel1.validation.UploadExcelToDB.excelToDb;

@Service
@RequiredArgsConstructor
public class ExcelUploadService {
    private final CustomersRepo customersRepo;

    public void save(MultipartFile file) {
        try {
            List<Customers> customersList= excelToDb(file);
            customersRepo.saveAll(customersList);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
