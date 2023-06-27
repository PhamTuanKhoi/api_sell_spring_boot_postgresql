package com.sell.tea.map;

import com.sell.tea.dtos.CreateProductDto;
import com.sell.tea.entities.CategoryEntity;
import com.sell.tea.entities.ProductEntity;
import com.sell.tea.exceptions.DataConstraintConflictException;
import com.sell.tea.repositories.ProductRepository;
import com.sell.tea.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductEntityAndProductRequestDtoMapper {

    private final ProductRepository productRepository;
    private final CategoryService categoryService;
    private final ModelMapper modelMapper;

    public void map(CreateProductDto createProductDto, ProductEntity productEntity){
        Boolean existsByName = this.productRepository.existsByName(createProductDto.getName());
        if(existsByName)
            throw new DataConstraintConflictException("name product is already exists");

        CategoryEntity category = this.categoryService.isEntityExists(createProductDto.getCategoryId());

        modelMapper.map(createProductDto, productEntity);
    }
}
