package com.sell.tea.dtos.request.order;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class CreateOrderDto {
    @NotNull
    @Min(value = 1, message = "From must be greater than zero")
    private int quantity;
    @NotNull
    @NotBlank
    private Long productId;

    private Long userId;
}
