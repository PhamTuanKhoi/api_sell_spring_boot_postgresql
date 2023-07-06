package com.sell.tea.repositories;

import com.sell.tea.entities.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CartRepository extends JpaRepository<CartEntity, Long>, JpaSpecificationExecutor<CartEntity> {

}
