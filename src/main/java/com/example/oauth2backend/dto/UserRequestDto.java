package com.example.oauth2backend.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserRequestDto {
    @JsonIgnore
    private String id;
    @JsonIgnore
    private String firstName;
    private String email;
    private String phoneNumber;
    private String passwords;
    private String picture;

}
