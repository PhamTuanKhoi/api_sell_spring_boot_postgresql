package com.sell.tea.repositories;

import com.sell.tea.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    boolean existsByName(String name);
}
