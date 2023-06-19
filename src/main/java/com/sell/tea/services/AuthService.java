package com.sell.tea.services;

import com.sell.tea.dtos.auth.RegisterRequest;
import com.sell.tea.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;

    public boolean register(RegisterRequest registerRequest) {
        return true;
    }
}
