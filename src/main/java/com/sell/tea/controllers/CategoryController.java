package com.sell.tea.controllers;

import com.sell.tea.dtos.request.CreateCategoryDto;
import com.sell.tea.dtos.response.CategoryResponseDto;
import com.sell.tea.dtos.response.ListEntityResponse;
import com.sell.tea.entities.CategoryEntity;
import com.sell.tea.services.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/category")
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping
    public List<CategoryEntity> findAll(@RequestParam(required = false) String search){
        return this.categoryService.findAll(search);
    }

    @PostMapping
    public ResponseEntity<CategoryEntity> create(@Valid @RequestBody CreateCategoryDto createCategoryDto){
        return ResponseEntity.ok(this.categoryService.create(createCategoryDto));
    }
}
