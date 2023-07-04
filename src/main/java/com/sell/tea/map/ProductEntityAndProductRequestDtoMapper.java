package com.sell.tea.map;

import com.sell.tea.dtos.request.product.CreateProductDto;
import com.sell.tea.dtos.request.product.UpdateProductDto;
import com.sell.tea.entities.CategoryEntity;
import com.sell.tea.entities.ProductEntity;
import com.sell.tea.exceptions.CatchException;
import com.sell.tea.exceptions.DataConstraintConflictException;
import com.sell.tea.exceptions.ResourceNotFoundException;
import com.sell.tea.repositories.ProductRepository;
import com.sell.tea.services.impl.CategoryServiceImpl;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductEntityAndProductRequestDtoMapper {

    private final ProductRepository productRepository;
    private final CategoryServiceImpl categoryService;
    private final ModelMapper modelMapper;

    public void map(CreateProductDto createProductDto, ProductEntity productEntity) {
//        validate return category
        this.validateByName(createProductDto.getName());
        CategoryEntity category = this.validateByCategoryId(createProductDto.getCategoryId());

        modelMapper.map(createProductDto, productEntity);
        productEntity.setCategory(category);
    }

    public void map(UpdateProductDto updateProductDto, ProductEntity productEntity) {
//        validate return category
        if (updateProductDto.getName() != null)
            if (!updateProductDto.getName().equals(productEntity.getName()))
                this.validateByName(updateProductDto.getName());

        CategoryEntity category = this.validateByCategoryId(updateProductDto.getCategoryId());

        modelMapper.map(updateProductDto, productEntity);

        if (category == null)
            throw new CatchException("category is null when map data");
        productEntity.setCategory(category);
    }


    private void validateByName(String name) {
        Boolean existsByName = this.productRepository.existsByName(name);
        if (existsByName)
            throw new DataConstraintConflictException("name product is already exists");
    }

    private CategoryEntity validateByCategoryId(Long categoryId) {
        CategoryEntity category = this.categoryService.isEntityExists(categoryId);
        if (category == null)
            throw new ResourceNotFoundException("category not found by id#" + categoryId);

        return category;
    }
}
