package com.example.oauth2backend.excel1;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomersRepo customersRepo;

    public void save(MultipartFile file) {
        try {
            List<Customers>customersList=ExcleHelper.excelToTutorials(file.getInputStream());
                customersRepo.saveAll(customersList);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public Page<Customers> findProductWithPagination(int pageNumber, int pageSize){
        return customersRepo.findAll(PageRequest.of(pageNumber,pageSize));
    }
}
