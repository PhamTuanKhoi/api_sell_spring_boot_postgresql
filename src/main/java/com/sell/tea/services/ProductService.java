package com.sell.tea.services;

import com.sell.tea.dtos.CreateProductDto;
import com.sell.tea.dtos.response.ListEntityResponse;
import com.sell.tea.entities.ProductEntity;

public interface ProductService {
    ListEntityResponse<ProductEntity> findAll();

    ProductEntity create(CreateProductDto createProductDto);

}
