package com.sell.tea.services;

import com.sell.tea.dtos.request.CreateCategoryDto;
import com.sell.tea.entities.CategoryEntity;
import com.sell.tea.exceptions.CatchException;
import com.sell.tea.map.CategoryEntityAndCategoryRequestDtoMapper;
import com.sell.tea.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryService {

    private final CategoryRepository categoryRepository;

    private final CategoryEntityAndCategoryRequestDtoMapper categoryEntityAndCategoryRequestDtoMapper;
    public CategoryEntity create(CreateCategoryDto createCategoryDto){
        CategoryEntity category = new CategoryEntity();
        categoryEntityAndCategoryRequestDtoMapper.map(createCategoryDto, category);

        try{
            CategoryEntity saved = this.categoryRepository.save(category);
            log.info("created a new category by id#"+saved.getId());
            return saved;
        }catch (Exception ex){
            throw new CatchException(ex.getMessage());
        }
    }
}
