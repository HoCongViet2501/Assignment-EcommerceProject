package com.assignment.springboot.controller;

import com.assignment.springboot.data.dto.ProductDTO;
import com.assignment.springboot.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
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
}
