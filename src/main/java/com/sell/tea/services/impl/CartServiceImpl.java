package com.sell.tea.services.impl;

import com.sell.tea.dtos.request.cart.UpdateCartDto;
import com.sell.tea.dtos.response.CartResponseDto;
import com.sell.tea.entities.CartEntity;
import com.sell.tea.exceptions.CatchException;
import com.sell.tea.exceptions.ResourceNotFoundException;
import com.sell.tea.map.CartEntityAndCartRequestDtoMapper;
import com.sell.tea.repositories.CartRepository;
import com.sell.tea.services.CartSerVice;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Slf4j
public class CartServiceImpl implements CartSerVice {

    private final CartRepository cartRepository;
    private final CartEntityAndCartRequestDtoMapper cartEntityAndCartRequestDtoMapper;
    private final ModelMapper modelMapper;

    @Override
    public List<CartResponseDto> findAll() {
        return this.cartRepository.findAll()
                .stream()
                .map(cart -> modelMapper.map(cart, CartResponseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public CartResponseDto findById(Long id) {
        return this.cartRepository.findById(id)
                .map(item -> modelMapper.map(item, CartResponseDto.class))
                .orElseThrow(() -> new ResourceNotFoundException("cart not found by id#" + id));
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
    public CartResponseDto update(Long id, UpdateCartDto updateCartDto) {
        CartEntity cartEntity = this.isEntityExist(id);
        cartEntityAndCartRequestDtoMapper.map(updateCartDto, cartEntity);

        try {
            CartEntity cart = this.cartRepository.save(cartEntity);
            log.info("updated a cart by id#" + cart.getId());

            CartResponseDto cartResponseDto = new CartResponseDto();
            modelMapper.map(cart, cartResponseDto);
            return cartResponseDto;
        } catch (Exception ex) {
            throw new CatchException(ex.getMessage());
        }
    }

    @Override
    public CartResponseDto delete(Long id) {
        CartEntity cartEntity = this.isEntityExist(id);
        try {
            this.cartRepository.delete(cartEntity);
            log.info("deleted a cart by id#" + id);
            CartResponseDto cartResponseDto = this.modelMapper.map(cartEntity, CartResponseDto.class);
            return cartResponseDto;
        } catch (Exception ex) {
            throw new CatchException(ex.getMessage());
        }
    }

    @Override
    public  CartEntity isEntityExist(Long id){
        return this.cartRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("cart not found by id#" + id));
    }
}
