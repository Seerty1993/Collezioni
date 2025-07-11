package com.emanuele.collezioni.ApiRest;

import io.swagger.v3.oas.annotations.Operation;
import com.emanuele.collezioni.model.Category;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.emanuele.collezioni.services.CategoryService;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
    @Operation(summary = "Recupera tutti gli item di collezione")
    @GetMapping
    public List<Category> all() {
        return categoryService.findAll();
    }

    @Operation(summary = "Elimina un item per ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Integer id) {
        categoryService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Recupera un item per ID")
    @GetMapping("/{id}")
    public Category get(@PathVariable Integer id) {
        return categoryService.findById(id);
    }
}
