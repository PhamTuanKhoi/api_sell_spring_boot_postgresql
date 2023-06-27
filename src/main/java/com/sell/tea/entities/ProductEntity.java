package com.sell.tea.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "product")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(
            nullable = false,
            unique = true
    )
    private String name;

    private String content;

    private Double price;

    @ManyToOne
    @JoinColumn(
            name = "category_id",
            foreignKey = @ForeignKey(name = "CATEGORY_ID_FK")
    )
    private CategoryEntity category;
}
