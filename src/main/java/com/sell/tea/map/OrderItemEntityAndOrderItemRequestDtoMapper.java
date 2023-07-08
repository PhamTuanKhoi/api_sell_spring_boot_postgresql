package com.sell.tea.map;

import com.sell.tea.dtos.request.order.CreateOrderDto;
import com.sell.tea.entities.OrderEntity;
import com.sell.tea.entities.OrderItemEntity;
import com.sell.tea.entities.ProductEntity;
import com.sell.tea.services.impl.ProductServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderItemEntityAndOrderItemRequestDtoMapper {
    private final ProductServiceImpl productService;
    public void map(
            OrderEntity order,
            CreateOrderDto createOrderDto,
            OrderItemEntity orderItemEntity
    ){
        if(!order.equals(null)){
            orderItemEntity.setOrder(order);
        }

        if(!createOrderDto.getProductId().equals(null)){
            ProductEntity product = this.productService.isEntityExist(createOrderDto.getProductId());
            orderItemEntity.setProduct(product);
            orderItemEntity.setPrice(product.getPrice());
        }
        orderItemEntity.setQuantity(createOrderDto.getQuantity());
    }
}
