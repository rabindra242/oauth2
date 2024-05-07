package com.example.oauth2backend.controller;

import com.example.oauth2backend.dto.FormRequestDto;
import com.example.oauth2backend.service.FormService;
import com.example.oauth2backend.utill.responseapi.ResponseApi;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class FormController {
    private final FormService formService;

    @PostMapping("/auth/form/post")
    public ResponseEntity<ResponseApi> saveUserFormDetails(@RequestBody FormRequestDto formRequestDto){
        var user=formService.saveUser(formRequestDto);
        return ResponseEntity.ok(user);

    }
}
