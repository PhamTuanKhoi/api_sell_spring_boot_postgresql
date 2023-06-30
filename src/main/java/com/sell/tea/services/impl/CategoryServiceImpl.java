package com.sell.tea.services.impl;

import com.sell.tea.dtos.request.CreateCategoryDto;
import com.sell.tea.entities.CategoryEntity;
import com.sell.tea.exceptions.CatchException;
import com.sell.tea.exceptions.ResourceNotFoundException;
import com.sell.tea.map.CategoryEntityAndCategoryRequestDtoMapper;
import com.sell.tea.repositories.CategoryRepository;
import com.sell.tea.services.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    private final CategoryEntityAndCategoryRequestDtoMapper categoryEntityAndCategoryRequestDtoMapper;
    private final ModelMapper modelMapper;


    @Override
    public Optional<CategoryEntity> findById(Long id) {
        return this.categoryRepository.findById(id);
    }

    @Override
    public List<CategoryEntity> findAll(String search) {
        return this.categoryRepository.findByCategorySearchName(search);
    }


    @Override
    public CategoryEntity create(CreateCategoryDto createCategoryDto) {
        CategoryEntity category = new CategoryEntity();
        categoryEntityAndCategoryRequestDtoMapper.map(createCategoryDto, category);

        try {
            CategoryEntity saved = this.categoryRepository.save(category);
            log.info("created a new category by id#" + saved.getId());
            return saved;
        } catch (Exception ex) {
            throw new CatchException(ex.getMessage());
        }
    }


    @Override
    public CategoryEntity isEntityExists(Long id) {
        return this.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("category not found by id#" + id));
    }
}
