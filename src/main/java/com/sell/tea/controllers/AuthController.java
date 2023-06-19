package com.sell.tea.controllers;

import com.sell.tea.dtos.auth.RegisterRequest;
import com.sell.tea.entities.UserEntity;
import com.sell.tea.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;


    @PostMapping("/register")
    public boolean register(@RequestBody RegisterRequest registerRequest){
        return authService.register(registerRequest);
    }
}
