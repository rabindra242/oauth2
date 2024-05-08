package com.example.oauth2backend.service;

import com.example.oauth2backend.dto.FormRequestDto;
import com.example.oauth2backend.dto.FormResponseDTO;
import com.example.oauth2backend.entity.FormDataEntity;
import com.example.oauth2backend.utill.responseapi.ResponseApi;

import java.util.List;

public interface FormService {
    public ResponseApi saveUser(FormRequestDto requestDto);

    List<FormResponseDTO> getFormsByEmail(String email);
}
