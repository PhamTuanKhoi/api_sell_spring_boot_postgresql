package com.sell.tea.dtos.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class ProductResponseDto {
    private Long id;
    private String name;
    private String content;
    private Double price;
}
