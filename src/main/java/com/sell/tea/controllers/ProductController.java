package com.sell.tea.controllers;


import com.sell.tea.dtos.request.product.CreateProductDto;
import com.sell.tea.dtos.request.product.QueryProductDto;
import com.sell.tea.dtos.response.ListEntityResponse;
import com.sell.tea.entities.ProductEntity;
import com.sell.tea.services.impl.ProductServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product")
public class ProductController {

    private final ProductServiceImpl productService;

    @GetMapping
    public ListEntityResponse<ProductEntity> findAll(
            @ModelAttribute("queryProductDto") QueryProductDto queryProductDto
    ) {
        return this.productService.findAll(queryProductDto);
    }

    @PostMapping
    public ResponseEntity<ProductEntity> create(@Valid @RequestBody CreateProductDto createProductDto) {
        return ResponseEntity.ok(productService.create(createProductDto));
    }
}
