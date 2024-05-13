package com.example.oauth2backend.excel;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/file")
@RequiredArgsConstructor
public class DataEntryController {
    private final DataEntityService dataEntityService;
    @PostMapping("/post")
    public ResponseEntity<?> upload(@RequestParam("file")MultipartFile file) throws IOException {
        if (FormatCheckerHelper.checkContentType(file)){
            dataEntityService.save(file);
            return ResponseEntity.ok(Map.of("messgae","File Uploaded SuccessFully"));


        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please upload a file");
    }
    @GetMapping("/get")
    public List<DataItemEntity> getDataEntries() {
        return dataEntityService.getAllProduct();
    }
}
