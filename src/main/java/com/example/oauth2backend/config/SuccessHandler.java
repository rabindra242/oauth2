package com.example.oauth2backend.config;

import com.example.oauth2backend.entity.UserEntity;
import com.example.oauth2backend.service.UserService;
import com.example.oauth2backend.utill.enumeration.RegistrationSource;
import com.example.oauth2backend.utill.enumeration.Role;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class SuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    private final UserService userService;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        OAuth2AuthenticationToken auth2Authentication = (OAuth2AuthenticationToken) authentication;
        if ("google".equals(auth2Authentication.getAuthorizedClientRegistrationId())) {
            DefaultOAuth2User principal = (DefaultOAuth2User) auth2Authentication.getPrincipal();
            Map<String, Object> attributes = principal.getAttributes();
            String email = attributes.getOrDefault("email", "").toString();
            String name = attributes.getOrDefault("name", "").toString();
            String picture = attributes.getOrDefault("picture", "").toString();
            userService.findByEmail(email).ifPresentOrElse(user -> {
                if (user.getRole() != null) {
                    DefaultOAuth2User newUser = new DefaultOAuth2User(List.of(new SimpleGrantedAuthority(user.getRole().name())),
                            attributes, "name");
                    Authentication securtityAuth = new OAuth2AuthenticationToken(newUser, List.of(new SimpleGrantedAuthority(user.getRole().name())),
                            auth2Authentication.getAuthorizedClientRegistrationId());
                    SecurityContextHolder.getContext().setAuthentication(securtityAuth);
                } else {
                    // Handle the case where the user's role is null
                }
            },()->{
                UserEntity userEntity= new UserEntity();
                userEntity.setEmail(email);
                userEntity.setRole(Role.ROLE_USER);
                userEntity.setRegistrationSource(RegistrationSource.GOOGLE);
                userEntity.setPicture(picture);
                userEntity.setFirstName(name);
                userService.saveUser(userEntity);
                DefaultOAuth2User newUser=new DefaultOAuth2User(List.of(new SimpleGrantedAuthority(userEntity.getRole().name())),
                        attributes,"name");
                Authentication securtityAuth= new OAuth2AuthenticationToken(newUser,List.of(new SimpleGrantedAuthority(userEntity.getRole().name())),
                        auth2Authentication.getAuthorizedClientRegistrationId());
                SecurityContextHolder.getContext().setAuthentication(securtityAuth);
            });

        }
        this.setAlwaysUseDefaultTargetUrl(true);
        this.setDefaultTargetUrl("http://localhost:5173/home");
        super.onAuthenticationSuccess(request, response, authentication);
    }

}
