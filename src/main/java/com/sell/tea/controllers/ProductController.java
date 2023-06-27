package com.sell.tea.controllers;


import com.sell.tea.dtos.CreateProductDto;
import com.sell.tea.entities.ProductEntity;
import com.sell.tea.services.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product")
public class ProductController {

    private final ProductService productService;
    @PostMapping
    public ResponseEntity<ProductEntity> create(@Valid @RequestBody CreateProductDto createProductDto){
        return ResponseEntity.ok(productService.create(createProductDto));
    }
}
