package com.example.oauth2backend.excel1;

import lombok.Data;

@Data
public class CustomerRequestDto {
    private Integer id;
    private String name;
    private String email;
    private String gender;
    private String contactNo;
    private String country;
}
