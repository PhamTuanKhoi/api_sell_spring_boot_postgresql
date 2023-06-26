package com.sell.tea.map;

import com.sell.tea.dtos.request.auth.RegisterRequest;
import com.sell.tea.dtos.request.user.UpdateUserDto;
import com.sell.tea.entities.UserEntity;
import com.sell.tea.entities.enums.Role;
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
public class UserEntityAndUserRequestDtoMapper {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    public void map(RegisterRequest registerRequest, UserEntity userEntity) {
        Boolean nameUser = this.userRepository.existsByName(registerRequest.getName());
        if (nameUser)
            throw new DataConstraintConflictException("name user is already exists");

        Boolean emailUser = this.userRepository.existsByEmail(registerRequest.getEmail());
        if (emailUser)
            throw new DataConstraintConflictException("email user is already exists");

        modelMapper.map(registerRequest, userEntity);
        userEntity.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        userEntity.setRole(Role.USER);
    }

    public void map(UpdateUserDto updateUserDto, UserEntity userEntity) {
        if (updateUserDto.getName() != updateUserDto.getNameOld()) {
            Boolean existsByName = userRepository.existsByName(updateUserDto.getName());
            if (existsByName)
                throw new DataConstraintConflictException("name user is already exists");
        }

        if (updateUserDto.getEmail() != updateUserDto.getEmailOld()) {
            Boolean existsByEmail = userRepository.existsByEmail(updateUserDto.getEmail());
            if (existsByEmail)
                throw new DataConstraintConflictException("email user is already exists");
        }

        modelMapper.map(updateUserDto, userEntity);
    }
}
