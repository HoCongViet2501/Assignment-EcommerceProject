package com.assignment.springboot.controller;

import com.assignment.springboot.dto.CategoryDTO;
import com.assignment.springboot.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    @Operation(summary = "get list category")
    public ResponseEntity<List<CategoryDTO>> getCategories() {
        List<CategoryDTO> listCategoryDto = this.categoryService.getCategories();
        return ResponseEntity.ok().body(listCategoryDto);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "create new category")
    public ResponseEntity<CategoryDTO> createCategory(CategoryDTO categoryDto) {
        this.categoryService.createCategory(categoryDto);
        return ResponseEntity.ok().body(categoryDto);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "delete category by id")
    public ResponseEntity<String> deleteCategory(@PathVariable int id) {
        this.categoryService.deleteCategory(id);
        return ResponseEntity.ok().body("deleted.category.have.id " + id);
    }

    @GetMapping("/name")
    @Operation(summary = "get category by name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "found.category.by.name"),
            @ApiResponse(responseCode = "404", description = "not.found.category")
    })
    public ResponseEntity<CategoryDTO> getCategoryByName(@RequestParam String name) {
        return ResponseEntity.ok().body(this.categoryService.findCategoryByName(name));
    }

    @PutMapping("/{id}")
    @Operation(summary = "update category by id")
    public ResponseEntity<CategoryDTO> updateCategory(@RequestBody CategoryDTO categoryDto, @PathVariable int id) {
        return ResponseEntity.ok().body(this.categoryService.updateCategory(categoryDto, id));
    }
}
