package com.sell.tea.services.impl;

import com.sell.tea.dtos.request.product.UpdateProductDto;
import com.sell.tea.dtos.response.ProductResponseDto;
import com.sell.tea.exceptions.ResourceNotFoundException;
import com.sell.tea.gobal.types.SortFilter;
import com.sell.tea.dtos.request.product.CreateProductDto;
import com.sell.tea.dtos.request.product.QueryProductDto;
import com.sell.tea.dtos.response.ListEntityResponse;
import com.sell.tea.entities.ProductEntity;
import com.sell.tea.exceptions.CatchException;
import com.sell.tea.map.ProductEntityAndProductRequestDtoMapper;
import com.sell.tea.repositories.ProductRepository;
import com.sell.tea.repositories.specification.ProductSpecification;
import com.sell.tea.services.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductEntityAndProductRequestDtoMapper productEntityAndProductRequestDtoMapper;
    private final ModelMapper modelMapper;



    @Override
    public ListEntityResponse<ProductResponseDto> findAll(QueryProductDto query){
        Integer page = query.getPage();
        int pageNumber = (page == null || page < 0) ? 0 : page - 1;
       try{
           SortFilter sortFilter = new SortFilter(query.getSortBy(),
                   query.getSortType() == null ? null : query.getSortType().name());
           PageRequest pageRequest = PageRequest.of(pageNumber, query.getLimit(), sortFilter.getSortType());

           Page<ProductEntity> productEntities  = this.productRepository.findAll(
                   ProductSpecification.filter(query.getName(), query.getCategoryIds()), pageRequest
           );

           List<ProductResponseDto> productResponseDto = productEntities.getContent()
                   .stream().map(item -> modelMapper.map(item, ProductResponseDto.class))
                   .collect(Collectors.toList());

           return new ListEntityResponse<ProductResponseDto>(
                   productResponseDto,
                   productEntities.getTotalElements(),
                   (long) productRepository.findAll().size(),
                   query.getLimit(),pageNumber + 1);
       }catch (Exception ex){
           throw new CatchException(ex.getMessage());
       }
    }


    @Override
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

    @Override
    public ProductEntity update(Long id, UpdateProductDto updateProductDto){
        ProductEntity productEntity =  this.isEntityExist(id);
        productEntityAndProductRequestDtoMapper.map(updateProductDto, productEntity);
        try{
            ProductEntity updated = this.productRepository.save(productEntity);
            log.info("updated a product by id#" + updated.getId());
            return  updated;
        }catch (Exception ex){
            throw new CatchException(ex.getMessage());
        }
    }

    public ProductEntity isEntityExist(Long id){
       return this.productRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("product not found by id#" + id));

    }
}
