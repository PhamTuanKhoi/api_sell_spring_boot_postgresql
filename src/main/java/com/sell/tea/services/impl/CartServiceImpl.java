package com.sell.tea.services.impl;

import com.sell.tea.dtos.request.cart.UpdateCartDto;
import com.sell.tea.dtos.response.CartResponseDto;
import com.sell.tea.entities.CartEntity;
import com.sell.tea.exceptions.CatchException;
import com.sell.tea.map.CartEntityAndCartRequestDtoMapper;
import com.sell.tea.repositories.CartRepository;
import com.sell.tea.services.CartSerVice;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j
public class CartServiceImpl implements CartSerVice {

    private final CartRepository cartRepository;
    private final CartEntityAndCartRequestDtoMapper cartEntityAndCartRequestDtoMapper;
    private final ModelMapper modelMapper;

    @Override
    public List<CartEntity> findAll() {
        return this.cartRepository.findAll();
    }

    @Override
    public CartResponseDto create(UpdateCartDto createCartDto) {
        CartEntity cartEntity = new CartEntity();
        cartEntityAndCartRequestDtoMapper.map(createCartDto, cartEntity);

        try {
            CartEntity cart = this.cartRepository.save(cartEntity);
            log.info("created a new cart by id#" + cart.getId());

            CartResponseDto cartResponseDto = new CartResponseDto();
            modelMapper.map(cart, cartResponseDto);
            return cartResponseDto;
        } catch (Exception ex) {
            throw new CatchException(ex.getMessage());
        }
    }

    @Override
    public CartEntity update(UpdateCartDto createCartDto) {
        CartEntity cartEntity = new CartEntity();
        return cartEntity;
    }
}
