package com.sell.tea.map;

import com.sell.tea.dtos.request.auth.RegisterRequest;
import com.sell.tea.entities.UserEntity;
import com.sell.tea.exceptions.DataConstraintConflictException;
import com.sell.tea.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class UserEntityAndUserResponseDtoMapper {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private PasswordEncoder passwordEncoder;

    public void map(RegisterRequest registerRequest, UserEntity userEntity) {
        Boolean nameUser = this.userRepository.existsByName(registerRequest.getName());
        if (nameUser)
            throw new DataConstraintConflictException("name user is already exists");

        Boolean emailUser = this.userRepository.existsByEmail(registerRequest.getEmail());
        if (emailUser)
            throw new DataConstraintConflictException("email user is already exists");

        modelMapper.map(registerRequest, userEntity);
        userEntity.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
    }
}
