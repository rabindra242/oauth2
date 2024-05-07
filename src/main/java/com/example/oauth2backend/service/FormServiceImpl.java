package com.example.oauth2backend.service;

import com.example.oauth2backend.dto.FormRequestDto;
import com.example.oauth2backend.entity.FormDataEntity;
import com.example.oauth2backend.entity.UserEntity;
import com.example.oauth2backend.repo.FormRepo;
import com.example.oauth2backend.repo.UserRepository;
import com.example.oauth2backend.utill.responseapi.ResponseApi;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FormServiceImpl implements FormService{
    private final FormRepo formRepo;
    @Override
    public ResponseApi saveUser(FormRequestDto requestDto) {
        ResponseApi responseApi = new ResponseApi();
        FormDataEntity data= FormDataEntity.builder()
                .dateOfBirth(requestDto.getDateOfBirth())
                .gender(requestDto.getGender())
                .jobType(requestDto.getJobType())
                .phoneNumber(requestDto.getPhoneNumber())
                .build();
        formRepo.save(data);
        responseApi.setMessage("User Successfully Save");
        return responseApi ;
    }
}
