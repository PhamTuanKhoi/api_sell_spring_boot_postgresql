package com.sell.tea.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "order_item")
public class OrderItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private int quantity;

    @ManyToOne
    @JoinColumn(
            name = "order_id",
            foreignKey = @ForeignKey(name = "ORDER_ID_FK"),
            nullable = false
    )
    private OrderEntity order;

    @ManyToOne
    @JoinColumn(
            name = "product_id",
            foreignKey = @ForeignKey(name = "PRODUCT_ID_FK"),
            nullable = false
    )
    private ProductEntity product;


}
