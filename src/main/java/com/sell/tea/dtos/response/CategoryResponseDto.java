package com.sell.tea.dtos.response;


import com.sell.tea.entities.ProductEntity;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class CategoryResponseDto {
    private Long id;
    private String name;
//    private List<ProductEntity> products;
}
