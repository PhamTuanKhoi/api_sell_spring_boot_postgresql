package com.sell.tea.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sell.tea.dtos.request.order.CreateOrderDto;
import com.sell.tea.dtos.request.order.OrderRequestStringify;
import com.sell.tea.dtos.response.OrderResponseDto;
import com.sell.tea.entities.OrderEntity;
import com.sell.tea.exceptions.CatchException;
import com.sell.tea.exceptions.ResourceNotFoundException;
import com.sell.tea.map.OrderEntityAndOrderRequestDtoMapper;
import com.sell.tea.repositories.OrderRepository;
import com.sell.tea.services.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderEntityAndOrderRequestDtoMapper orderEntityAndOrderRequestDtoMapper;
    private final OrderItemServiceImpl orderItemService;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public List<OrderResponseDto> findAll(){
        return  this.orderRepository.findAll()
                .stream().map(item->modelMapper.map(item, OrderResponseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public OrderResponseDto create(Long userId, OrderRequestStringify orderRequestStringify)
            throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        CreateOrderDto[] createOrderDtos = objectMapper.readValue(
                orderRequestStringify.getOrdersStringify(), CreateOrderDto[].class
        );

        OrderEntity orderEntity = new OrderEntity();
        orderEntityAndOrderRequestDtoMapper.map(userId, createOrderDtos, orderEntity);

        try {
            OrderEntity order = this.orderRepository.save(orderEntity);
            log.info("created a new order by id#" + orderEntity.getId());
            this.orderItemService.create(order, createOrderDtos);
            return modelMapper.map(order, OrderResponseDto.class);
        } catch (Exception ex) {
            throw new CatchException(ex.getMessage());
        }
    }

    public OrderEntity isEntityExist(Long id) {
        return this.orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("order not found by id#" + id));
    }
}
