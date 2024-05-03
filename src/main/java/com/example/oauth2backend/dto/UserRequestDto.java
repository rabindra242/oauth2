package com.example.oauth2backend.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequestDto {
    private String id;
    private String firstName;
    private String email;
    private String picture;

}
