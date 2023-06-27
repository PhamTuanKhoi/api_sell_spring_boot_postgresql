package com.sell.tea.repositories;

import com.sell.tea.entities.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
    CategoryEntity findByName(String name);

    Boolean existsByName(String name);
}
