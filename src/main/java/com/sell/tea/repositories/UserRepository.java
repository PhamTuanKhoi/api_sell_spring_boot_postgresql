package com.sell.tea.repositories;

import com.sell.tea.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long>, JpaSpecificationExecutor<UserEntity> {
    Optional<UserEntity> findByEmail(String email);
    Optional<UserEntity> findByName(String name);

    Optional<UserEntity> findById(Long id);

    Boolean existsByName(String name);
    Boolean existsByEmail(String email);

}
