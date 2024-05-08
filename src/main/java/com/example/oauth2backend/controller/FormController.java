package com.example.oauth2backend.controller;

import com.example.oauth2backend.dto.FormRequestDto;
import com.example.oauth2backend.service.FormServiceImpl;
import com.example.oauth2backend.utill.responseapi.ResponseApi;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class FormController {
    private final FormServiceImpl formService;


    @PostMapping("/auth/form/post")
    public ResponseEntity<ResponseApi> saveUser(@RequestBody FormRequestDto requestDto){
        formService.saveUser(requestDto);
        return ResponseEntity.ok(formService.saveUser(requestDto));
    }
}
