package com.sell.tea.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sell.tea.dtos.request.order.OrderRequestStringify;
import com.sell.tea.dtos.response.OrderResponseDto;
import com.sell.tea.exceptions.CatchException;
import com.sell.tea.services.impl.OrderServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/order")
public class OrderController {
    private final OrderServiceImpl orderService;

    @GetMapping
    public List<OrderResponseDto> findAll(){
        return this.orderService.findAll();
    }

    @PostMapping
    public OrderResponseDto create(
            HttpServletRequest request,
            @RequestBody OrderRequestStringify orderRequestStringify)
            throws JsonProcessingException {
        Long userId = (Long) request.getAttribute("userId");
        if (userId == null) throw new CatchException("id user -> authentication not found");
       return this.orderService.create(userId, orderRequestStringify);
    }
}
