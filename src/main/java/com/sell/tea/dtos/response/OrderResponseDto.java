package com.sell.tea.dtos.response;

import com.sell.tea.entities.enums.OrderStatus;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class OrderResponseDto {
    private Double totalPrice;
    private OrderStatus status;
    private UserResponse user;
    private List<OrderItemResponse> orderItemEntities;

    @Getter
    @Setter
    @RequiredArgsConstructor
    public static class UserResponse{
        private Long id;
        private String name;
        private String email;
        private String avatar;
    }

    @Getter
    @Setter
    @RequiredArgsConstructor
    public static class OrderItemResponse{
        private String id;
        private ProductResponse product;

        @Getter
        @Setter
        @RequiredArgsConstructor
        public static class ProductResponse{
            private Long id;
            private String name;
        }
    }
}
