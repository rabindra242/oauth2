package com.example.oauth2backend.service;

import co.elastic.clients.elasticsearch.security.User;
import com.example.oauth2backend.dto.UserRequestDto;
import com.example.oauth2backend.entity.UserEntity;
import com.example.oauth2backend.repo.UserRepository;
import com.sun.security.auth.UserPrincipal;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
@Slf4j
public class OauthUserServiceImpl extends DefaultOAuth2UserService {
    private final UserRepository userRepository;
    @Override
    @SneakyThrows
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        log.trace("Load user{}",userRequest);
        OAuth2User oAuth2User= super.loadUser(userRequest);
        return processOAuth2User(userRequest,oAuth2User);
    }

    private OAuth2User processOAuth2User(OAuth2UserRequest userRequest, OAuth2User oAuth2User) {
        UserRequestDto userRequestDto=UserRequestDto.builder()
                .firstName(oAuth2User.getAttributes().get("name").toString())
                .email(oAuth2User.getAttributes().get("email").toString())
                .id(oAuth2User.getAttributes().get("sub").toString())
                .picture(oAuth2User.getAttributes().get("picture").toString())
                .build();

        Optional<UserEntity> userEntityOptional=userRepository.findByEmail(userRequestDto.getEmail());
        UserEntity user=userEntityOptional
                .map(ex->updateExistingUser(ex,userRequestDto))
                .orElseGet(()->registerNewUser(oAuth2User,userRequest));

        return


    }

    private UserEntity registerNewUser(OAuth2UseraaaaRequest oAuth2User, OAuth2UserRequest userRequest) {
        UserEntity userEntity=UserEntity.builder()
                .email(oAuth2User)


                .build();
    }

    private UserEntity updateExistingUser(UserEntity ex, UserRequestDto userRequestDto) {
        ex.setFirstName(userRequestDto.getFirstName());
        ex.setPicture(userRequestDto.getPicture());
        ex.setEmail(userRequestDto.getEmail());
        return userRepository.save(ex);
    }
}
