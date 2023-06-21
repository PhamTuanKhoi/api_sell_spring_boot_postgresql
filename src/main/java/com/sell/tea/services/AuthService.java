package com.sell.tea.services;

import com.sell.tea.dtos.request.auth.RegisterRequest;
import com.sell.tea.dtos.request.auth.LoginRequest;
import com.sell.tea.dtos.response.AuthenticationResponse;
import com.sell.tea.entities.UserEntity;
import com.sell.tea.map.UserEntityAndUserResponseDtoMapper;
import com.sell.tea.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {
    private final UserRepository userRepository;
    private final UserEntityAndUserResponseDtoMapper userEntityAndUserResponseDtoMapper;

    public RegisterRequest register(RegisterRequest registerRequest) {
            UserEntity user = new UserEntity();
            userEntityAndUserResponseDtoMapper.map(registerRequest, user);
        try {
            userRepository.save(user);
            return registerRequest;
        } catch (Exception error) {
            log.error("Exception" + error);
        }
        return null;
    }


    public AuthenticationResponse login(LoginRequest loginRequest){
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                loginRequest.getEmail(),
                loginRequest.getPassword()
        );

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        System.out.println(authenticationToken);

        AuthenticationResponse authenticationResponse = new AuthenticationResponse();

        return authenticationResponse;
    }
}
