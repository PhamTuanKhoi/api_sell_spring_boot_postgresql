package com.sell.tea.services;

import com.sell.tea.dtos.CreateProductDto;
import com.sell.tea.entities.ProductEntity;
import com.sell.tea.exceptions.CatchException;
import com.sell.tea.map.ProductEntityAndProductRequestDtoMapper;
import com.sell.tea.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductEntityAndProductRequestDtoMapper productEntityAndProductRequestDtoMapper;


    public ProductEntity create(CreateProductDto createProductDto){
        ProductEntity product = new ProductEntity();
        productEntityAndProductRequestDtoMapper.map(createProductDto, product);

        try{
            ProductEntity saved = this.productRepository.save(product);
            log.info("create a new product by id#" + saved.getId());
            return  saved;
        }catch (Exception ex){
            throw new CatchException(ex.getMessage());
        }
    }
}
