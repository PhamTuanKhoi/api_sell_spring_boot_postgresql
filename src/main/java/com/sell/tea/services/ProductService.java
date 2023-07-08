package com.sell.tea.services;

import com.sell.tea.dtos.request.product.CreateProductDto;
import com.sell.tea.dtos.request.product.QueryProductDto;
import com.sell.tea.dtos.request.product.UpdateProductDto;
import com.sell.tea.dtos.response.ListEntityResponse;
import com.sell.tea.dtos.response.ProductResponseDto;
import com.sell.tea.entities.ProductEntity;

public interface ProductService {

    ListEntityResponse<ProductResponseDto> findAll(QueryProductDto query);

    ProductEntity create(CreateProductDto createProductDto);

    ProductEntity update(Long id, UpdateProductDto updateProductDto);
}
