package com.example.oauth2backend;

import com.example.oauth2backend.excel1.CustomerRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Oauth2BackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(Oauth2BackendApplication.class, args);
    }

}
