package com.example.oauth2backend.utill.mapper;

import com.example.oauth2backend.dto.UserRequestDto;
import com.example.oauth2backend.entity.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RequestResponseMapper {

    UserEntity convertUserDtoToUser(UserRequestDto userRequestDto);
}
