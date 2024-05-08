//package com.example.oauth2backend.service;
//
//import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
//import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
//import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
//import org.springframework.stereotype.Service;
//
//@Service
//public class OAuth2TokenRequester {
//
//    private OAuth2AuthorizedClientManager authorizedClientManager;
//
//    public OAuth2TokenRequester(OAuth2AuthorizedClientManager oAuth2AuthorizedClientManager) {
//        this.authorizedClientManager = oAuth2AuthorizedClientManager;
//    }
//
//
//    public OAuth2AuthorizedClient authorize(String clientRegistrationId, String principal) {
//        OAuth2AuthorizeRequest req = OAuth2AuthorizeRequest.withClientRegistrationId(clientRegistrationId).principal(principal).build();
//        return authorizedClientManager.authorize(req);
//    }
//
//    public String requestAccessToken() {
//        OAuth2AuthorizedClient authorizedClient = authorize("google", "principle");
//        if (authorizedClient != null && authorizedClient.getAccessToken() != null) {
//            String jwt = authorizedClient.getAccessToken().getTokenValue();
//            System.out.println(jwt);
//            return jwt;
//        } else {
//            // Handle the case where authorization failed
//            System.out.println("Authorization failed or access token is null");
//            return null;
//        }
//    }
//
//}