package com.example.oauth2backend.controller;

import com.example.oauth2backend.dto.FormRequestDto;
import com.example.oauth2backend.service.FormServiceImpl;
import com.example.oauth2backend.utill.responseapi.ResponseApi;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class FormController {
    private final FormServiceImpl formService;
//    private final OAuth2TokenRequester oAuth2TokenRequester;


    @PostMapping("/auth/form/post")
    public ResponseEntity<ResponseApi> saveUser(@RequestBody FormRequestDto requestDto, HttpServletRequest request){
        ResponseApi responseApi=new ResponseApi();
        HttpSession httpSession = request.getSession();
        String email=httpSession.getAttribute("email").toString();
//        Long id = (Long) httpSession.getAttribute("email");
        System.out.println(email);
        requestDto.setEmail(email);
        formService.saveUser(requestDto);
        responseApi.setMessage("User Successfully Saved");
        return ResponseEntity.ok(responseApi);
    }

    @GetMapping("/userCredentials")
    public ResponseEntity<ResponseApi> get(HttpServletRequest request){
        ResponseApi responseApi= new ResponseApi();
        HttpSession httpSession=request.getSession();
        String email=httpSession.getAttribute("email").toString();
        responseApi.setResponse(email);


        return ResponseEntity.ok(responseApi);
    }
    @GetMapping("/get-formData")
    public ResponseEntity<ResponseApi> getFormData(HttpServletRequest request){
        ResponseApi responseApi= new ResponseApi();
        HttpSession httpSession=request.getSession();
        String email=httpSession.getAttribute("email").toString();
        var data=formService.getFormsByEmail(email);
        responseApi.setResponse(data);
        return ResponseEntity.ok(responseApi);
    }

}
