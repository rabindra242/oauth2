package com.example.oauth2backend.dto;

import com.example.oauth2backend.entity.UserEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.Email;
import lombok.Data;
import lombok.NonNull;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class FormRequestDto {
    @NonNull
    private String phoneNumber;
    @NonNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;
    @NonNull
    private String jobType;
    @NonNull
    private String gender;
    @Email
    private String email;

}
