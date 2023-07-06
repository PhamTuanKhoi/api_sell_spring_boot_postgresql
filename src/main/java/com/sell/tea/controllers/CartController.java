package com.sell.tea.controllers;

import com.sell.tea.dtos.request.cart.UpdateCartDto;
import com.sell.tea.dtos.response.CartResponseDto;
import com.sell.tea.entities.CartEntity;
import com.sell.tea.exceptions.CatchException;
import com.sell.tea.services.impl.CartServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartServiceImpl cartService;

    @GetMapping
    public ResponseEntity<List<CartResponseDto>> findAll() {
        return ResponseEntity.ok(this.cartService.findAll());
    }

    @GetMapping("/{id}")
    public CartResponseDto findById(@PathVariable("id") Long id) {
        return this.cartService.findById(id);
    }

    @PostMapping
    public ResponseEntity<CartResponseDto> create(
            HttpServletRequest request,
            @Valid @RequestBody UpdateCartDto createCartDto) {

        Long userId = (Long) request.getAttribute("userId");
        if (userId == null) throw new CatchException("userId not found interceptor");

        createCartDto.setUserId(userId);
        return ResponseEntity.ok(this.cartService.create(createCartDto));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CartResponseDto> update(
            @PathVariable("id") Long id,
            @RequestBody UpdateCartDto updateCartDto) {
        return ResponseEntity.ok(this.cartService.update(id, updateCartDto));
    }


    @DeleteMapping("/{id}")
    public CartResponseDto delete(@PathVariable("id") Long id){
        return this.cartService.delete(id);
    }
}
