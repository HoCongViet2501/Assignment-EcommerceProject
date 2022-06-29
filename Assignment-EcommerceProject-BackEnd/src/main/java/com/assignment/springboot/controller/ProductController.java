package com.assignment.springboot.controller;

import com.assignment.springboot.data.dto.ProductDTO;
import com.assignment.springboot.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    @Operation(summary = "get all product")
    public List<ProductDTO> getProducts() {
        return productService.getProducts();
    }

    @GetMapping("/{id}")
    @Operation(summary = "find product by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "found the product")
    })
    public ProductDTO findProductById(@PathVariable int id) {
        return this.productService.findProductById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "create new product")
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO productDTO) {
        this.productService.saveProduct(productDTO);
        return ResponseEntity.ok().body(productDTO);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "delete product by id")
    public ResponseEntity<String> deleteProduct(@PathVariable int id) {
        this.productService.deleteProduct(id);
        return ResponseEntity.ok().body("Delete success product have id " + id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "update product")
    public ResponseEntity<ProductDTO> updateProduct(@RequestBody ProductDTO productDTO, @PathVariable int id) {
        this.productService.updateProduct(productDTO, id);
        return ResponseEntity.ok().body(productDTO);
    }
}
