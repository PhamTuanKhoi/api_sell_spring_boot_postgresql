package com.sell.tea.map;


import com.sell.tea.dtos.request.order.CreateOrderDto;
import com.sell.tea.entities.OrderEntity;
import com.sell.tea.entities.ProductEntity;
import com.sell.tea.entities.UserEntity;
import com.sell.tea.entities.enums.OrderStatus;
import com.sell.tea.services.impl.ProductServiceImpl;
import com.sell.tea.services.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderEntityAndOrderRequestDtoMapper {

    private final ProductServiceImpl productService;
    private final UserServiceImpl userService;
    public void map(Long userId,CreateOrderDto[] createOrderDtos, OrderEntity order){ 
        Double totalPrice = 0.0;
        
        for (CreateOrderDto createOrderDto: createOrderDtos){
            if (!createOrderDto.getProductId().equals(null)) {
                ProductEntity product = this.productService.isEntityExist(createOrderDto.getProductId());
                Double total = product.getPrice() * createOrderDto.getQuantity();
                totalPrice += total;
            }
        }

        if(!userId.equals(null)){
            UserEntity user = this.userService.isEntityExists(userId);
            order.setUser(user);
        }
        order.setTotalPrice(totalPrice);
        order.setStatus(OrderStatus.ORDER);

    }
}
