package com.example.oauth2backend.excel1;

import com.example.oauth2backend.dto.FormResponseDTO;

import java.io.ByteArrayInputStream;
import java.util.List;

public interface ExportToExcel {
    ByteArrayInputStream exportUserDb(List<CustomerRequestDto> customerRequestDtos);
    List<CustomerRequestDto> getAllFormData();





}
