package com.example.oauth2backend.service;

import com.example.oauth2backend.dto.UserRequestDto;
import com.example.oauth2backend.utill.responseapi.ResponseApi;

public interface UserService {

    ResponseApi saveUser(UserRequestDto userRequestDto);
}
