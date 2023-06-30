package com.sell.tea.map;

import com.sell.tea.dtos.request.category.CreateCategoryDto;
import com.sell.tea.entities.CategoryEntity;
import com.sell.tea.exceptions.DataConstraintConflictException;
import com.sell.tea.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CategoryEntityAndCategoryRequestDtoMapper {

    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    public void map (CreateCategoryDto createCategoryDto, CategoryEntity categoryEntity){
        Boolean existsByName = this.categoryRepository.existsByName(createCategoryDto.getName());
        if(existsByName)
            throw new DataConstraintConflictException("name category is already exists");

        modelMapper.map(createCategoryDto, categoryEntity);
    }
}
