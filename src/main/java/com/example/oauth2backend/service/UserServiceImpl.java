package com.example.oauth2backend.service;

import com.example.oauth2backend.dto.UserRequestDto;
import com.example.oauth2backend.entity.UserEntity;
import com.example.oauth2backend.repo.UserRepository;
import com.example.oauth2backend.utill.responseapi.ResponseApi;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.example.oauth2backend.utill.constrains.Message.USER_SAVED;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Override
    public void saveUser(UserEntity user) {

        userRepository.save(user);


    }

    @Override
    public Optional<UserEntity> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
