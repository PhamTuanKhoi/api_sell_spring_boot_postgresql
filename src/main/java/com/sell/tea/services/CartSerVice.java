package com.sell.tea.services;

import com.sell.tea.dtos.request.cart.UpdateCartDto;
import com.sell.tea.dtos.response.CartResponseDto;
import com.sell.tea.entities.CartEntity;

import java.util.List;

public interface CartSerVice {
    List<CartEntity> findAll();

    CartResponseDto create(UpdateCartDto createCartDto);

    CartEntity update(UpdateCartDto createCartDto);
}
