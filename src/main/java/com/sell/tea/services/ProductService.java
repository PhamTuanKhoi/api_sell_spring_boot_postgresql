package com.sell.tea.services;

import com.sell.tea.dtos.CreateProductDto;
import com.sell.tea.dtos.response.ListEntityResponse;
import com.sell.tea.entities.CategoryEntity;
import com.sell.tea.entities.ProductEntity;
import com.sell.tea.exceptions.CatchException;
import com.sell.tea.map.ProductEntityAndProductRequestDtoMapper;
import com.sell.tea.repositories.ProductRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductEntityAndProductRequestDtoMapper productEntityAndProductRequestDtoMapper;


    public ListEntityResponse<ProductEntity> findAll(){
        List<ProductEntity> productEntities  = this.productRepository.findAll();
//        Specification<ProductEntity> productSpecification = (Root<ProductEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
//
//        }
        return new ListEntityResponse<ProductEntity>(productEntities, (long) productEntities.size(), 1,1);
    }

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
