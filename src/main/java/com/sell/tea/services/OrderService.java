package com.sell.tea.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sell.tea.dtos.request.order.OrderRequestStringify;
import com.sell.tea.dtos.response.OrderResponseDto;

import java.util.List;

public interface OrderService {

    List<OrderResponseDto> findAll();

    OrderResponseDto create(Long userId, OrderRequestStringify orderRequestStringify) throws JsonProcessingException;
}
