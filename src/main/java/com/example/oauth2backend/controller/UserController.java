//package com.example.oauth2backend.controller;
//
//import com.example.oauth2backend.dto.LoginRequestDTO;
//import com.example.oauth2backend.dto.UserRequestDto;
//import com.example.oauth2backend.service.UserService;
//import com.example.oauth2backend.utill.responseapi.ResponseApi;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@CrossOrigin
//@Slf4j
//@RequiredArgsConstructor
//public class UserController {
//    private final UserService userService;
//    private final UserManageMentService userManageMentService;
//
//
//    @PostMapping("auth/register")
//    public ResponseEntity<ResponseApi> register(@RequestBody UserRequestDto userRequestDto){
//        return ResponseEntity.ok(userManageMentService.registerUser(userRequestDto));
//    }
//    @PostMapping("auth/login")
//    public ResponseEntity<ResponseApi> login(@RequestBody LoginRequestDTO loginRequestDTO){
//        return ResponseEntity.ok(userManageMentService.loginUser(loginRequestDTO));
//    }
//    @GetMapping("/oauth2/authorization/google")
//    public String login(){
//        return "hasdbads";
//    }
//
//}
