package org.example.canon.controller;

import lombok.RequiredArgsConstructor;
import org.example.canon.controller.response.loginResponse.LoginResponse;
import org.example.canon.repository.UserRepository;
import org.example.canon.service.CustomOAuth2UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequiredArgsConstructor
public class LoginController {

    private final UserRepository userRepository;
    private final CustomOAuth2UserService customOAuth2UserService;


    @PostMapping("/login")
    public ResponseEntity<LoginResponse> LoginWithGoogle(@AuthenticationPrincipal OAuth2User user) {


        LoginResponse loginResponse = new LoginResponse(user);
        return ResponseEntity.ok(loginResponse);
    }


}
