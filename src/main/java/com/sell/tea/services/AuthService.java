package com.sell.tea.services;

import com.sell.tea.dtos.request.auth.LoginRequest;
import com.sell.tea.dtos.request.auth.RegisterRequest;
import com.sell.tea.dtos.response.AuthenticationResponse;
import com.sell.tea.dtos.response.UserResponseDto;

public interface AuthService {
    UserResponseDto register(RegisterRequest registerRequest);
    AuthenticationResponse login(LoginRequest loginRequest);
}
