package com.example.oauth2backend.utill.responseapi;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseApi<T> {
    private String message;
    private int code;
    private String token;
    private T Response;
}
