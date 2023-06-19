package com.sell.tea.services;

import com.sell.tea.entities.UserEntity;
import com.sell.tea.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public Optional<UserEntity> findById(String id){
        return this.userRepository.findByName(id);
    }

    public Optional<UserEntity> findByName(String name){
        return this.userRepository.findByName(name);
    }

    public Optional<UserEntity> findByEmail(String email){
        return this.userRepository.findByEmail(email);
    }
}
