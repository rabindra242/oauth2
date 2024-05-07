package com.example.oauth2backend.utill.email;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class EmailConfig {
    private  final JavaMailSender mailSender ;
    public  void sendSimpleMail(String email){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Your email is used Is that you?");
        message.setText("You have logged in in Zakipoint App at:"+ LocalDateTime.now());
        mailSender.send(message);
    }
}
