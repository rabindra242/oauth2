package com.example.oauth2backend.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.context.SecurityContextHolder;

@Configuration
public class UtillConfig {


    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
    @Bean
    public SecurityContextHolder securityContextHolder(){
        return new SecurityContextHolder();
    }
}
