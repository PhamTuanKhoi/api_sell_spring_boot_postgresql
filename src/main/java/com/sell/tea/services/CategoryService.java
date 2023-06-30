package com.sell.tea.services;

import com.sell.tea.dtos.request.category.CreateCategoryDto;
import com.sell.tea.entities.CategoryEntity;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
      Optional<CategoryEntity> findById(Long id);

    List<CategoryEntity> findAll(String search);
    CategoryEntity create(CreateCategoryDto createCategoryDto);
    CategoryEntity isEntityExists(Long id);
}
