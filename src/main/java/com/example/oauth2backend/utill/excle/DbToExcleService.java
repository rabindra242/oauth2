package com.example.oauth2backend.utill.excle;

import com.example.oauth2backend.dto.FormResponseDTO;
import com.example.oauth2backend.entity.FormDataEntity;

import java.io.ByteArrayInputStream;
import java.util.List;

public interface DbToExcleService {
    ByteArrayInputStream export(List<FormResponseDTO> dataEntities);
    List<FormResponseDTO> getAllFormData();
}
