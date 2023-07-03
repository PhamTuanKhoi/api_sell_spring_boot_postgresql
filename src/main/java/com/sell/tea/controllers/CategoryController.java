package com.sell.tea.controllers;

import com.sell.tea.dtos.request.category.CreateCategoryDto;
import com.sell.tea.entities.CategoryEntity;
import com.sell.tea.services.impl.CategoryServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/category")
public class CategoryController {
    private final CategoryServiceImpl categoryService;

    @GetMapping
    public List<CategoryEntity> findAll(@RequestParam(required = false) String search){
        return this.categoryService.findAll(search);
    }

    @PostMapping
    public ResponseEntity<CategoryEntity> create(@Valid @RequestBody CreateCategoryDto createCategoryDto){
        return ResponseEntity.ok(this.categoryService.create(createCategoryDto));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CategoryEntity> update(
            @PathVariable("id") Long id,
            @Valid @RequestBody CreateCategoryDto createCategoryDto){
        return ResponseEntity.ok(this.categoryService.update(id,createCategoryDto));
    }

    @DeleteMapping("/{id}")
    public  CategoryEntity delete(@PathVariable("id") Long id){
        return this.categoryService.delete(id);
    }
}
