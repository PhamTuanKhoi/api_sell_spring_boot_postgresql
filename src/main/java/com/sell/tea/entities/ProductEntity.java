package com.sell.tea.entities;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "product")
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")
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

    @OneToMany(mappedBy = "product")
    private List<CartEntity> cartEntities = new ArrayList<>();

    @OneToMany(mappedBy = "product")
    private List<OrderItemEntity> orderItemEntities = new ArrayList<>();
}
