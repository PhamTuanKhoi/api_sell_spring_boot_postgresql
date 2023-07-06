package com.sell.tea.services;

import com.sell.tea.dtos.request.cart.UpdateCartDto;
import com.sell.tea.dtos.response.CartResponseDto;
import com.sell.tea.entities.CartEntity;

import java.util.List;

public interface CartSerVice {
    List<CartResponseDto> findAll();

    CartEntity findById(Long id);

    CartResponseDto create(UpdateCartDto createCartDto);

    CartResponseDto update(Long id, UpdateCartDto createCartDto);
}
