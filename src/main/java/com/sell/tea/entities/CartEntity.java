package com.sell.tea.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "cart")
public class CartEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int quantity;

    @ManyToOne
    @JoinColumn(
            name = "product_id",
            foreignKey = @ForeignKey(name = "PRODUCT_ID_FK")
    )
    private ProductEntity product;

    @ManyToOne
    @JoinColumn(
            name = "user_id",
            foreignKey = @ForeignKey(name = "USER_ID_FK")
    )
    private UserEntity user;
}
