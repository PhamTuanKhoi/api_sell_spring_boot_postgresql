package com.sell.tea.controllers;


import com.sell.tea.dtos.CreateProductDto;
import com.sell.tea.dtos.response.ListEntityResponse;
import com.sell.tea.entities.CategoryEntity;
import com.sell.tea.entities.ProductEntity;
import com.sell.tea.services.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product")
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ListEntityResponse<ProductEntity> findAll(){
        return this.productService.findAll();
    }
    @PostMapping
    public ResponseEntity<ProductEntity> create(@Valid @RequestBody CreateProductDto createProductDto){
        return ResponseEntity.ok(productService.create(createProductDto));
    }
}