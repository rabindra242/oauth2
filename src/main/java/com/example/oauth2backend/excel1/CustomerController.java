package com.example.oauth2backend.excel1;

import com.example.oauth2backend.utill.responseapi.ResponseApi;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/upload")
    public ResponseApi<Customers> upload(@RequestParam("file") MultipartFile file) throws IOException {
        ResponseApi responseApi= new ResponseApi();
        if (ExcleHelper.hasExcleFormat(file)){
            customerService.save(file);
             responseApi.setMessage("File uploadedSuccessfully");
             responseApi.setResponse(file.getOriginalFilename());
             return responseApi;
        }
        else {
            responseApi.setMessage("File not upload sucessfully");
            return responseApi;
        }
    }
    @GetMapping("/getAllCustomer/{offSet}/{pageSize}")
    private ResponseEntity<ResponseApi<List<Customers>>> getProductWithSort(@PathVariable int offSet, @PathVariable int pageSize){
        ResponseApi<List<Customers>> responseApi = new ResponseApi<>();
        Page<Customers> customersWithPagination = customerService.findProductWithPagination(offSet, pageSize);
        List<Customers> customersList = customersWithPagination.getContent();
        if (customersList.size() > pageSize) {
            customersList = customersList.subList(0, pageSize);
        }
        responseApi.setMessage("Success");
        responseApi.setCode(200);
        responseApi.setResponse(customersList);
        return ResponseEntity.ok().body(responseApi);
    }
}
