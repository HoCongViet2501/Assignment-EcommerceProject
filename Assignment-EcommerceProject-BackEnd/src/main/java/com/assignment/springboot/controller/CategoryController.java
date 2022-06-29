package com.assignment.springboot.controller;

import com.assignment.springboot.data.dto.CategoryDTO;
import com.assignment.springboot.exception.ResourceNotFoundException;
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
    private CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    @Operation(summary = "get all category")
    public ResponseEntity<?> getAllCategory() {
        List<CategoryDTO> listCategory = this.categoryService.getCategories();
        if (listCategory.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResourceNotFoundException("Can't get all category"));
        } else {
            return ResponseEntity.ok().body(listCategory);
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "create new category")
    public ResponseEntity<?> createCategory(CategoryDTO categoryDTO) {
        boolean save = this.categoryService.saveCategory(categoryDTO);
        if (save)
            return ResponseEntity.ok().body(categoryDTO);
        else
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResourceNotFoundException("can't save category"));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "delete category by id")
    public ResponseEntity<?> deleteCategory(@PathVariable int id) {
        boolean delete = this.categoryService.deleteCategory(id);
        if (delete)
            return ResponseEntity.ok().body("deleted category have id " + id);
        else
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResourceNotFoundException("Can't delete category have id " + id));
    }

    @GetMapping("/name")
    @Operation(summary = "get category by name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "found category by name"),
            @ApiResponse(responseCode = "404",description = "not found category")
    })
    public ResponseEntity<?> getCategoryByName(@RequestParam String name) {
        CategoryDTO categoryDTO = this.categoryService.findCategoryByName(name);
        return ResponseEntity.ok().body(categoryDTO);
    }

    @PutMapping("/{id}")
    @Operation(summary = "update category")
    public ResponseEntity<CategoryDTO> updateCategory(@RequestBody CategoryDTO categoryDTO, @PathVariable int id) {
        CategoryDTO categoryDTOUpdate = this.categoryService.updateCategory(categoryDTO, id);
        return ResponseEntity.ok().body(categoryDTOUpdate);
    }
}
