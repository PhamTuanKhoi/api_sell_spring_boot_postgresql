package com.sell.tea.services;

import com.sell.tea.dtos.auth.RegisterRequest;
import com.sell.tea.entities.UserEntity;
import com.sell.tea.map.UserEntityAndUserResponseDtoMapper;
import com.sell.tea.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {
    private final UserRepository userRepository;
    private final UserEntityAndUserResponseDtoMapper userEntityAndUserResponseDtoMapper;

    public RegisterRequest register(RegisterRequest registerRequest) {
        try {
            UserEntity user = new UserEntity();

            userEntityAndUserResponseDtoMapper.map(registerRequest, user);

            userRepository.save(user);

            return registerRequest;
        } catch (Exception error) {
            log.error("Exception" + error);
        }
        return null;
    }
}
