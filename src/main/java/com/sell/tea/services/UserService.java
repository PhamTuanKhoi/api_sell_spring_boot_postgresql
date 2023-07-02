package com.sell.tea.services;

import com.sell.tea.dtos.request.user.UpdateUserDto;
import com.sell.tea.dtos.response.ListEntityResponse;
import com.sell.tea.dtos.response.UserResponseDto;
import com.sell.tea.entities.UserEntity;

import java.util.Optional;

public interface UserService {

    Optional<UserEntity> findById(Long id);

    UserEntity findByEmail(String email);

    ListEntityResponse<UserResponseDto> findAll(String name, Integer page,
                                                Integer limit, String sortBy, String sortType);

    UserResponseDto update(Long id, UpdateUserDto updateUserDto);

    UserResponseDto delete(Long id);

    UserEntity isEntityExists(Long id);
}
