package com.sell.tea.dtos.request.product;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class UpdateProductDto {
    private String name;
    private String content;
    private Double price;
    private Long categoryId;
}
