package com.example.oauth2backend.excel1.validation;

import com.example.oauth2backend.excel1.Customers;
import com.example.oauth2backend.excel1.ExcleHelper;
import com.example.oauth2backend.excel1.validation.exception.ExcelFieldValidation;
import com.example.oauth2backend.utill.responseapi.ResponseApi;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import static com.example.oauth2backend.excel1.ExcleHelper.hasExcleFormat;

@RestController
@RequiredArgsConstructor
@RequestMapping("/excel/file")
public class ExcelUploadController {
    private final ExcelUploadService excelUploadService;

    @PostMapping("/upload")
    public ResponseApi<List<Customers>> upload(@RequestParam("file") MultipartFile file) throws IOException {
        ResponseApi responseApi = new ResponseApi();
        if (hasExcleFormat(file)){
            try {
                responseApi.setResponse(excelUploadService.save(file));
                responseApi.setMessage("File uploadedSuccessfully");
                return responseApi;
            }catch (ExcelFieldValidation e){
              throw new ExcelFieldValidation(e.getMessage());
            }
        }
        else{
            responseApi.setResponse("File validation failed");
            return responseApi;
        }
    }

}
