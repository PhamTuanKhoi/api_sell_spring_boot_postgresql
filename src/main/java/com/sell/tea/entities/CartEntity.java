package com.sell.tea.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cart")
public class CartEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int quantity;

    @ManyToOne
    @JoinColumn(
            name = "product_id",
            foreignKey = @ForeignKey(name = "PRODUCT_ID_FK"),
            nullable = false
    )
    private ProductEntity product;

    @ManyToOne
    @JoinColumn(
            name = "user_id",
            foreignKey = @ForeignKey(name = "USER_ID_FK"),
            nullable = false
    )
    private UserEntity user;
}
