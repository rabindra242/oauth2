package com.example.oauth2backend.service;

import com.example.oauth2backend.dto.UserRequestDto;
import com.example.oauth2backend.entity.UserEntity;
import com.example.oauth2backend.utill.responseapi.ResponseApi;
import org.apache.catalina.User;

import java.util.Optional;

public interface UserService {

   void saveUser(UserEntity user);

    Optional<UserEntity> findByEmail(String email);
}
