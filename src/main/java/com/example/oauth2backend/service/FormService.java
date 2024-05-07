package com.example.oauth2backend.service;

import com.example.oauth2backend.dto.FormRequestDto;
import com.example.oauth2backend.utill.responseapi.ResponseApi;

public interface FormService {
    public ResponseApi saveUser(FormRequestDto requestDto);
}
