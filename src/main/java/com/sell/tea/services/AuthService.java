package com.sell.tea.services;

import com.sell.tea.dtos.request.auth.LoginRequest;
import com.sell.tea.dtos.request.auth.RegisterRequest;
import com.sell.tea.dtos.response.AuthenticationResponse;
import com.sell.tea.entities.UserEntity;
import com.sell.tea.map.UserEntityAndUserRequestDtoMapper;
import com.sell.tea.repositories.UserRepository;
import com.sell.tea.security.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {

    private final UserRepository userRepository;
    private final UserEntityAndUserRequestDtoMapper userEntityAndUserResponseDtoMapper;
    private final JwtService jwtService;
    private final ModelMapper modelMapper;
    private final AuthenticationManager authenticationManager;


    public RegisterRequest register(RegisterRequest registerRequest) {
        UserEntity user = new UserEntity();
        userEntityAndUserResponseDtoMapper.map(registerRequest, user);

        userRepository.save(user);
        return registerRequest;
    }


    public AuthenticationResponse login(LoginRequest loginRequest) {
        var authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        String token = jwtService.generateToken(userDetails);

        AuthenticationResponse authenticationResponse = new AuthenticationResponse();
        modelMapper.map(userDetails, authenticationResponse);
        authenticationResponse.setAccessToken(token);

        return authenticationResponse;
    }
}
