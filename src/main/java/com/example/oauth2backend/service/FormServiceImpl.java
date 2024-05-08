package com.example.oauth2backend.service;

import com.example.oauth2backend.config.SuccessHandler;
import com.example.oauth2backend.dto.FormRequestDto;
import com.example.oauth2backend.dto.FormResponseDTO;
import com.example.oauth2backend.entity.FormDataEntity;
import com.example.oauth2backend.repo.FormRepo;
import com.example.oauth2backend.repo.UserRepository;
import com.example.oauth2backend.utill.responseapi.ResponseApi;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class FormServiceImpl implements FormService {
    private final FormRepo formRepo;
    private final ModelMapper modelMapper;
    @Override
    public ResponseApi saveUser(FormRequestDto requestDto) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        ResponseApi responseApi = new ResponseApi();
        FormDataEntity data = FormDataEntity.builder()
                .dateOfBirth(requestDto.getDateOfBirth())
                .gender(requestDto.getGender())
                .jobType(requestDto.getJobType())
                .email(requestDto.getEmail())
                .phoneNumber(requestDto.getPhoneNumber())
                .build();
        formRepo.save(data);
        responseApi.setMessage("User Data Successfully Submitted");
        return responseApi;
    }
    @Override
    public List<FormResponseDTO> getFormsByEmail(String email) {

        List<FormDataEntity> data = formRepo.findAllByEmail(email);
        return data.stream()
                .map(entity -> modelMapper.map(entity, FormResponseDTO.class))
                .collect(Collectors.toList());
    }
}
