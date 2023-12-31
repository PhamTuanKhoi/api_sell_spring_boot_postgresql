package com.sell.tea.controllers;


import com.sell.tea.dtos.request.product.CreateProductDto;
import com.sell.tea.dtos.request.product.QueryProductDto;
import com.sell.tea.dtos.request.product.UpdateProductDto;
import com.sell.tea.dtos.response.ListEntityResponse;
import com.sell.tea.dtos.response.ProductResponseDto;
import com.sell.tea.entities.ProductEntity;
import com.sell.tea.gobal.dtos.UserAttributeDto;
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
    public ListEntityResponse<ProductResponseDto> findAll(
            @ModelAttribute("queryProductDto") QueryProductDto queryProductDto
    ) {
        return this.productService.findAll(queryProductDto);
    }

    @PostMapping
    public ResponseEntity<ProductEntity> create(
            @Valid @RequestBody CreateProductDto createProductDto) {
        return ResponseEntity.ok(productService.create(createProductDto));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ProductEntity> update(
            @PathVariable("id") Long id,
            @RequestBody UpdateProductDto updateProductDto) {
        return ResponseEntity.ok(productService.update(id, updateProductDto));
    }
}
