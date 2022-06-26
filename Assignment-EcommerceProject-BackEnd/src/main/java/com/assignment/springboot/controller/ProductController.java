package com.assignment.springboot.controller;

import com.assignment.springboot.data.dto.ProductDTO;
import com.assignment.springboot.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Validated
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private ProductService productService;
    @GetMapping
    public List<ProductDTO> getProducts(){
        return productService.getProducts();
    }
    @GetMapping("/{id}")
    public ProductDTO findProductById(@PathVariable  int id){
        return this.productService.findProductById(id);
    }
    @PostMapping
    public ResponseEntity<ProductDTO> saveProduct(@Valid @RequestBody ProductDTO productDTO){
        this.productService.saveProduct(productDTO);
        return ResponseEntity.ok().body(productDTO);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable int id){
        this.productService.deleteProduct(id);
        return ResponseEntity.ok().body("Delete success");
    }
}
