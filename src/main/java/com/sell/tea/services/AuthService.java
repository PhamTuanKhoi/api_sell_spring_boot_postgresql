package com.sell.tea.services;

import com.sell.tea.dtos.auth.RegisterRequest;
import com.sell.tea.entities.UserEntity;
import com.sell.tea.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;

    public Optional<UserEntity> register(RegisterRequest registerRequest){

    }
}
