package com.sell.tea.services;

import com.sell.tea.dtos.request.order.CreateOrderDto;
import com.sell.tea.entities.OrderEntity;

public interface OrderItemService {

    void create(OrderEntity order, CreateOrderDto[] createOrderDtos);
}
