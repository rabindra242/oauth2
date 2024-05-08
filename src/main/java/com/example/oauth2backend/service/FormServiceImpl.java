package com.example.oauth2backend.service;

import com.example.oauth2backend.dto.FormRequestDto;
import com.example.oauth2backend.entity.FormDataEntity;
import com.example.oauth2backend.entity.UserEntity;
import com.example.oauth2backend.repo.FormRepo;
import com.example.oauth2backend.repo.UserRepository;
import com.example.oauth2backend.utill.responseapi.ResponseApi;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class FormServiceImpl implements FormService {

    private final FormRepo formRepo;
    private final UserRepository userRepo;
    private final SecurityContextHolder securityContextHolder;


    @Override
    public ResponseApi saveUser(FormRequestDto requestDto) {
        ResponseApi responseApi = new ResponseApi();

        // Retrieve the authentication object from the SecurityContextHolder
        Authentication authentication = securityContextHolder.getContext().getAuthentication();

        // Check if the user is authenticated
        if (authentication != null && authentication.isAuthenticated()) {
            // Extract the principal object from the authentication
            Object principal = authentication.getPrincipal();

            // Check if the principal object is of type DefaultOAuth2User
            if (principal instanceof DefaultOAuth2User) {
                DefaultOAuth2User oauth2User = (DefaultOAuth2User) principal;

                // Retrieve user attributes (e.g., email)
                String email = oauth2User.getAttribute("email");

                // Use the email to fetch the corresponding user entity from the database
                Optional<UserEntity> userEntity = userRepo.findByEmail(email);

                // Check if the user entity exists
                if (userEntity != null) {
                    // Create FormDataEntity and associate it with the retrieved userEntity
                    FormDataEntity data = FormDataEntity.builder()
                            .dateOfBirth(requestDto.getDateOfBirth())
                            .gender(requestDto.getGender())
                            .jobType(requestDto.getJobType())
                            .phoneNumber(requestDto.getPhoneNumber())
                            .build();

                    // Save the form data
                    formRepo.save(data);

                    responseApi.setMessage("User Successfully Saved");
                } else {
                    responseApi.setMessage("User not found");
                }
            } else {
                responseApi.setMessage("Authentication principal is not of type DefaultOAuth2User");
            }
        } else {
            responseApi.setMessage("Authentication is null or not authenticated");
        }

        return responseApi;
    }
}



