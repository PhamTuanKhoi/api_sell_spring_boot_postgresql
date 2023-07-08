package com.sell.tea.dtos.response;

import com.sell.tea.entities.enums.OrderStatus;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class OrderResponseDto {
    private Double totalPrice;
    private OrderStatus orderStatus;
}
