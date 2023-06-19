package com.sell.tea.repositories;

import com.sell.tea.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmail(String email);
    Optional<UserEntity> findByName(String name);

    Boolean existsByEmail(String email);
    Boolean existsByName(String name);
}
