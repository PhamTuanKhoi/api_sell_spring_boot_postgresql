package com.sell.tea.map;

import com.sell.tea.dtos.request.cart.UpdateCartDto;
import com.sell.tea.entities.CartEntity;
import com.sell.tea.entities.ProductEntity;
import com.sell.tea.entities.UserEntity;
import com.sell.tea.services.impl.ProductServiceImpl;
import com.sell.tea.services.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CartEntityAndCartRequestDtoMapper {
    private final ProductServiceImpl productService;
    private final UserServiceImpl userService;
    private final ModelMapper modelMapper;

    public void map(UpdateCartDto createCartDto, CartEntity cartEntity) {
        if (!createCartDto.getUserId().equals(null)) {
            UserEntity user = this.userService.isEntityExists(createCartDto.getUserId());
            cartEntity.setUser(user);
        }

        if (!createCartDto.getProductId().equals(null)) {
            ProductEntity product =this.productService.isEntityExist(createCartDto.getProductId());
            cartEntity.setProduct(product);
        }

        modelMapper.map(createCartDto, cartEntity);
    }
}
