package com.sell.tea.controllers;

import com.sell.tea.dtos.auth.RegisterRequest;
import com.sell.tea.entities.UserEntity;
import com.sell.tea.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;


    @GetMapping
    public Optional<UserEntity> register(@RequestBody RegisterRequest registerRequest){

        return authService.register(registerRequest);
    }
}
