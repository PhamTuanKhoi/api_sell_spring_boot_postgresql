package com.sell.tea.controllers;

import com.sell.tea.dtos.request.auth.RegisterRequest;
import com.sell.tea.dtos.request.auth.LoginRequest;
import com.sell.tea.dtos.response.AuthenticationResponse;
import com.sell.tea.dtos.response.UserResponseDto;
import com.sell.tea.entities.UserEntity;
import com.sell.tea.services.impl.AuthServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthServiceImpl authService;


    @PostMapping("/register")
    public ResponseEntity<UserResponseDto> register(@Valid @RequestBody RegisterRequest registerRequest){
        return ResponseEntity.ok(authService.register(registerRequest));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@Valid @RequestBody LoginRequest loginRequest){
        return ResponseEntity.ok(authService.login(loginRequest));
    }

    @PostMapping("/current-user")
    public Optional<UserResponseDto> currentUser(HttpServletRequest request){
        UserEntity user = (UserEntity) request.getAttribute("user");
        if(user == null) return null;
        ModelMapper modelMapper = new ModelMapper();
        UserResponseDto userResponseDto = modelMapper.map(user, UserResponseDto.class);

        return Optional.ofNullable(userResponseDto);
    }
}
