package com.example.oauth2backend.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UtillConfig {


    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
