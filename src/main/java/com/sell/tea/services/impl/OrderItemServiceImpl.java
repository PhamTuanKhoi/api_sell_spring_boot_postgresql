package com.sell.tea.services.impl;

import com.sell.tea.dtos.request.order.CreateOrderDto;
import com.sell.tea.entities.OrderEntity;
import com.sell.tea.entities.OrderItemEntity;
import com.sell.tea.exceptions.CatchException;
import com.sell.tea.map.OrderItemEntityAndOrderItemRequestDtoMapper;
import com.sell.tea.repositories.OrderItemRepository;
import com.sell.tea.services.OrderItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderItemServiceImpl implements OrderItemService {
    private final OrderItemEntityAndOrderItemRequestDtoMapper orderItemEntityAndOrderItemRequestDtoMapper;
    private final OrderItemRepository orderItemRepository;

    @Override
    public void create(OrderEntity order, CreateOrderDto[] createOrderDtos) {
        for (CreateOrderDto createOrderDto : createOrderDtos) {
            OrderItemEntity orderItemEntity = new OrderItemEntity();
            orderItemEntityAndOrderItemRequestDtoMapper.map(order,
                    createOrderDto, orderItemEntity);
            try {
                OrderItemEntity orderItem = this.orderItemRepository.save(orderItemEntity);
                log.info("created a new order-item by id#" + orderItem.getId());
            } catch (Exception ex) {
                throw new CatchException(ex.getMessage());
            }
        }
    }
}
