package com.assignment.springboot.controller;

import com.assignment.springboot.data.dto.CategoryDTO;
import com.assignment.springboot.exception.ResourceNotFoundException;
import com.assignment.springboot.service.CategoryService;
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
    public ResponseEntity<?> getAllCategory(){
        List<CategoryDTO> listCategory=this.categoryService.getCategories();
        if(listCategory.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResourceNotFoundException("Can't get all category"));
        }else{
            return ResponseEntity.ok().body(listCategory);
        }
    }
    @PostMapping
    public ResponseEntity<?> saveCategory(CategoryDTO categoryDTO){
      boolean save=  this.categoryService.saveCategory(categoryDTO);
      if(save)
        return ResponseEntity.ok().body(categoryDTO);
      else
          return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResourceNotFoundException("can't save category"));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable int id){
        boolean delete=this.categoryService.deleteCategory(id);
        if(delete)
            return ResponseEntity.ok().body("deleted category have id "+ id);
        else
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResourceNotFoundException("Can't delete category have id "+id));
    }
    @GetMapping("/name")
    public ResponseEntity<?> getCategoryByName(@RequestParam String name){
        CategoryDTO categoryDTO=this.categoryService.findCategoryByName(name);
        return ResponseEntity.ok().body(categoryDTO);
    }
    @PutMapping("/{id}")
    public ResponseEntity<CategoryDTO> updateCategory(@RequestBody CategoryDTO categoryDTO,@PathVariable int id){
        CategoryDTO categoryDTOUpdate=this.categoryService.updateCategory(categoryDTO,id);
        return ResponseEntity.ok().body(categoryDTOUpdate);
    }
}
