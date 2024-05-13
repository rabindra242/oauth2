package com.example.oauth2backend.excel1;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomersRepo customersRepo;

    public void save(MultipartFile file) throws IOException {
        try {
            List<Customers> customersList=ExcleHelper.excelToTutorials(file.getInputStream()){
                customersRepo.save(customersList);
            }
        }
    }
}
