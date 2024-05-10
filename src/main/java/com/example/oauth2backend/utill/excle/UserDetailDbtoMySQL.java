package com.example.oauth2backend.utill.excle;

import com.example.oauth2backend.dto.UserRequestDto;

import java.io.ByteArrayInputStream;
import java.util.List;

public interface UserDetailDbtoMySQL {
    ByteArrayInputStream exportUserDB(List<UserRequestDto> userRequestDtoList);
    List<UserRequestDto> getAllUserDetails();
}
