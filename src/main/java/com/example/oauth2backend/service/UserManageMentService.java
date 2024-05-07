package com.example.oauth2backend.service;

import com.example.oauth2backend.dto.LoginRequestDTO;
import com.example.oauth2backend.dto.UserRequestDto;
import com.example.oauth2backend.entity.UserEntity;
import com.example.oauth2backend.repo.UserRepository;
import com.example.oauth2backend.utill.JwtUtil;
import com.example.oauth2backend.utill.email.EmailConfig;
import com.example.oauth2backend.utill.enumeration.RegistrationSource;
import com.example.oauth2backend.utill.enumeration.Role;
import com.example.oauth2backend.utill.responseapi.ResponseApi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserManageMentService {
    private final UserRepository userRepo;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtill;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;
    private final EmailConfig emailConfig;

    public ResponseApi registerUser(UserRequestDto reqRes) {
        ResponseApi responseApi = new ResponseApi();
        Optional<UserEntity> user1=userRepo.findByEmail(reqRes.getEmail());
        if (user1.isPresent()){
            return null;
        }else {
            UserEntity user = new UserEntity();
            user.setFirstName(reqRes.getFirstName());
            user.setPasswords(passwordEncoder.encode(reqRes.getPasswords()));
            user.setEmail(reqRes.getEmail());
            user.setRole(Role.ROLE_USER);
            user.setRegistrationSource(RegistrationSource.LOGIN);
            user.setPhoneNumber(reqRes.getPhoneNumber());
            userRepo.save(user);
            emailConfig.sendSimpleMail(reqRes.getEmail());
            responseApi.setResponse(user);
            return responseApi;
        }
    }

    public ResponseApi loginUser(LoginRequestDTO reqRes) {
        ResponseApi responseApi = new ResponseApi();
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(reqRes.getEmail(), reqRes.getPassword()));

        UserEntity userEntity=userRepo.findByEmail(reqRes.getEmail()).orElse(null);
        if (reqRes.getEmail().isBlank()||reqRes.getEmail().isEmpty()){
            return null;
        }
        emailConfig.sendSimpleMail(reqRes.getEmail());
        var jwt=jwtUtill.generateToken(userEntity);
        responseApi.setResponse(jwt);
        log.info(jwt);
        return responseApi;
    }

    public ResponseApi forgetPassword(String email, String password) {
        ResponseApi responseApi = new ResponseApi();
        Optional<UserEntity> user=userRepo.findByEmail(email);
        if (user.get().getPasswords().equals(password)){
             responseApi.setResponse( "Password are same please Use another Password");
        }
        if (user.get().getEmail().equals(email)){
            UserEntity userEntity=new UserEntity();
            userEntity.setPasswords(passwordEncoder.encode(password));
            update(userEntity);
        }

        responseApi.setResponse("Successfuly Changed");
        return responseApi;
    }

    private void update(UserEntity userEntity){}


}
