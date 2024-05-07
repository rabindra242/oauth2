package com.example.oauth2backend.controller;

import com.example.oauth2backend.dto.LoginRequestDTO;
import com.example.oauth2backend.dto.UserRequestDto;
import com.example.oauth2backend.entity.UserEntity;
import com.example.oauth2backend.service.UserManageMentService;
import com.example.oauth2backend.service.UserService;
import com.example.oauth2backend.utill.enumeration.Role;
import com.example.oauth2backend.utill.responseapi.ResponseApi;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@Slf4j
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserManageMentService userManageMentService;


    @PostMapping("auth/register")
    public ResponseEntity<ResponseApi> register(@RequestBody UserRequestDto userRequestDto){
        return ResponseEntity.ok(userManageMentService.registerUser(userRequestDto));
    }
    @PostMapping("auth/login")
    public ResponseEntity<ResponseApi> login(@RequestBody LoginRequestDTO loginRequestDTO){
        return ResponseEntity.ok(userManageMentService.loginUser(loginRequestDTO));
    }
    @GetMapping("/oauth2/authorization/google")
    public String login(){
        return "hasdbads";
    }
}
