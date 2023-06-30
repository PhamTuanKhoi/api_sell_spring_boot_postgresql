package com.sell.tea.dtos.request.product;

import com.sell.tea.entities.CategoryEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class CreateProductDto {
    @NotBlank
    private String name;
    private String content;
    @NotNull
    private Double price;
    @NotNull
    private Long categoryId;
}
