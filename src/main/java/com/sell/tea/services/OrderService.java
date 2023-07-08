package com.sell.tea.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sell.tea.dtos.request.order.OrderRequestStringify;
import com.sell.tea.entities.OrderEntity;

public interface OrderService {

    OrderEntity create(Long userId, OrderRequestStringify orderRequestStringify) throws JsonProcessingException;
}
