package com.sell.tea.dtos.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class CartResponseDto {
    private Long id;
    private int quantity;
    private ProductResponseDto product;
    private UserResponseDto user;
}
