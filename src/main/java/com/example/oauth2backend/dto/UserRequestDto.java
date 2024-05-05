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
    private String id;
    private String firstName;
    private String email;
    @JsonIgnore
    private String picture;
    private String passwords;

}
