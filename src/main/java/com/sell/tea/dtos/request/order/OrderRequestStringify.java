package com.sell.tea.dtos.request.order;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderRequestStringify {
    @NotBlank
    private String ordersStringify;
}
