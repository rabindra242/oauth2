package com.example.oauth2backend.service;

import com.example.oauth2backend.dto.UserRequestDto;
import com.example.oauth2backend.entity.UserEntity;
import com.example.oauth2backend.repo.UserRepository;
import com.example.oauth2backend.utill.responseapi.ResponseApi;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import static com.example.oauth2backend.utill.constrains.Message.USER_SAVED;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final ModelMapper modelMapper;


    private final UserRepository userRepository;
    @Override
    public ResponseApi saveUser(UserRequestDto userRequestDto) {
        ResponseApi responseApi= new ResponseApi<>();
//        var user=UserEntity.builder()
//                .email(userRequestDto.getEmail())
//                .firstName(userRequestDto.getFirstName())
//                        .build();
//        userRepository.save(user);
        UserEntity user = modelMapper.map(userRequestDto, UserEntity.class);
        userRepository.save(user);
        responseApi.setCode(HttpStatus.ACCEPTED.value());
        responseApi.setMessage(USER_SAVED);
        return responseApi;
    }
}
