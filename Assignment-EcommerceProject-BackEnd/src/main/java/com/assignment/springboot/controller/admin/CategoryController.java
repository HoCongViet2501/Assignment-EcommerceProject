package com.assignment.springboot.controller.admin;

import com.assignment.springboot.dto.request.CategoryDtoRequest;
import com.assignment.springboot.dto.response.CategoryDtoResponse;
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
    public ResponseEntity<List<CategoryDtoResponse>> getCategories() {
        List<CategoryDtoResponse> listCategoryDto = this.categoryService.getCategories();
        return ResponseEntity.ok().body(listCategoryDto);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "create new category")
    public ResponseEntity<CategoryDtoResponse> createCategory(CategoryDtoRequest categoryDtoRequest) {
        CategoryDtoResponse categoryDtoResponse= this.categoryService.createCategory(categoryDtoRequest);
        return ResponseEntity.ok().body(categoryDtoResponse);
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
    public ResponseEntity<CategoryDtoResponse> getCategoryByName(@RequestParam String name) {
        return ResponseEntity.ok().body(this.categoryService.findCategoryByName(name));
    }

    @PutMapping("/{id}")
    @Operation(summary = "update category by id")
    public ResponseEntity<CategoryDtoResponse> updateCategory(@RequestBody CategoryDtoRequest categoryDtoRequest, @PathVariable int id) {
        CategoryDtoResponse categoryDtoResponse=this.categoryService.updateCategory(categoryDtoRequest, id);
        return ResponseEntity.ok().body(categoryDtoResponse);
    }
}
