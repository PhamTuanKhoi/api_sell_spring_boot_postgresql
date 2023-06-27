package com.sell.tea.controllers;

import com.sell.tea.dtos.request.CreateCategoryDto;
import com.sell.tea.entities.CategoryEntity;
import com.sell.tea.services.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/category")
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<CategoryEntity> create(@Valid @RequestBody CreateCategoryDto createCategoryDto){
        return ResponseEntity.ok(this.categoryService.create(createCategoryDto));
    }
}
