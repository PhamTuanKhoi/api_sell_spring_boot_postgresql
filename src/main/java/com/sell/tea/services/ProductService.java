package com.sell.tea.services;

import com.sell.tea.dtos.request.product.CreateProductDto;
import com.sell.tea.dtos.request.product.QueryProductDto;
import com.sell.tea.dtos.response.ListEntityResponse;
import com.sell.tea.entities.ProductEntity;

public interface ProductService {

    ListEntityResponse<ProductEntity> findAll(QueryProductDto query);

    ProductEntity create(CreateProductDto createProductDto);

}
