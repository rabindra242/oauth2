package com.example.oauth2backend.controller;

import ch.qos.logback.core.model.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;

public class Oauth2Controller {
    @CrossOrigin(origins = "http://localhost:5173") // Replace with your frontend URL
    @GetMapping("/oauth2/authorization/google")
    public String googleAuthorization() {
        // Your controller logic here
        return "Google authorization endpoint";
    }
}
