package com.sell.tea.dtos.request.cart;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class UpdateCartDto {
    @NotNull
    private int quantity;

    @NotNull
    private Long productId;

    private Long userId;
}
