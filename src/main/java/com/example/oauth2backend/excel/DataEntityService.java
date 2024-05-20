package com.example.oauth2backend.excel;

import lombok.RequiredArgsConstructor;
import lombok.experimental.Helper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DataEntityService {
    private final DataEntityRepository dataEntityRepository;

    public void save(MultipartFile file) throws IOException {
        List<DataItemEntity> dataItemEntities=FormatCheckerHelper.convertExcelToList(file.getInputStream());
        dataEntityRepository.saveAll(dataItemEntities);

    }
    public List<DataItemEntity> getAllProduct(){
        return dataEntityRepository.findAll();

    }
}
