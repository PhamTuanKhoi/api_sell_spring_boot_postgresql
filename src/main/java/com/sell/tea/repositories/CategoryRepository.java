package com.sell.tea.repositories;

import com.sell.tea.entities.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long>, JpaSpecificationExecutor<CategoryEntity> {
    CategoryEntity findByName(String name);

    Boolean existsByName(String name);


    @Query("SELECT c FROM category c WHERE c.name")
    List<CategoryEntity> findByCategorySearchName(@Param("search") String search);
}
