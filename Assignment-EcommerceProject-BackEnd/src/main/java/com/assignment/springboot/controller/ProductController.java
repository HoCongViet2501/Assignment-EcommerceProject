package com.assignment.springboot.controller;

import com.assignment.springboot.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
public class ProductController {
	@Autowired
	private ProductService productService;
	
	@GetMapping("/brand/{brandName}")
	@Operation(summary = "get product by brandName")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "302", description = "found.product.by.brandName..success"),
			@ApiResponse(responseCode = "404", description = "not.found.product")
	})
	public ResponseEntity<Object> getProductsByBrandName(@PathVariable String brandName
			, @RequestParam(defaultValue = "0", value = "page", required = false) int page
			, @RequestParam(defaultValue = "12", value = "size", required = false) int size) {
		return ResponseEntity.status(HttpStatus.FOUND).body(productService.getProductsByBrandName(brandName, page, size));
	}
	
	@Operation(summary = "get product by categoryName")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "302", description = "found.product.by.categoryName.success"),
			@ApiResponse(responseCode = "404", description = "not.found.product")
	})
	@GetMapping("/category/{categoryName}")
	public ResponseEntity<Object> getProductsByCategoryName(@PathVariable String categoryName
			, @RequestParam(defaultValue = "0", value = "page", required = false) int page
			, @RequestParam(defaultValue = "12", value = "size", required = false) int size) {
		return ResponseEntity.status(HttpStatus.FOUND).body(productService.getProductsByCategoryName(categoryName, page, size));
	}
	
	@Operation(summary = "get product by gender")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "302", description = "found.product.by.gender.success"),
			@ApiResponse(responseCode = "404", description = "not.found.product")
	})
	@GetMapping("/gender/{gender}")
	public ResponseEntity<Object> getProductsByGender(@PathVariable String gender
			, @RequestParam(defaultValue = "0", value = "page", required = false) int page
			, @RequestParam(defaultValue = "12", value = "size", required = false) int size) {
		return ResponseEntity.status(HttpStatus.FOUND).body(productService.getProductsByGender(gender, page, size));
	}
	
	@GetMapping
	@Operation(summary = "get list product order by price asc ")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "get.products.success"),
			@ApiResponse(responseCode = "404", description = "not.found.product")
	})
	public ResponseEntity<Object> getProducts(
			@RequestParam(defaultValue = "0", value = "page", required = false) int page
			, @RequestParam(defaultValue = "12", value = "size", required = false) int size) {
		return ResponseEntity.status(HttpStatus.OK).body(productService.getAllProductOrderByPriceAsc(page, size));
	}
	
	@GetMapping("/name/{name}")
	@Operation(summary = "get products by name")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "302", description = "found.products.by.Name.success"),
			@ApiResponse(responseCode = "404", description = "not.found.products")
	})
	public ResponseEntity<Object> getProductsByName(@PathVariable String name
			, @RequestParam(defaultValue = "0", value = "page", required = false) int page
			, @RequestParam(defaultValue = "12", value = "size", required = false) int size) {
		return ResponseEntity.status(HttpStatus.FOUND).body(productService.getProductsByName(name, page, size));
	}
	
	@GetMapping("/price/{startPrice}/{endPrice}")
	@Operation(summary = "get products by filter price")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "302", description = "filter.product.by.price.success"),
			@ApiResponse(responseCode = "404", description = "not.found.product")
	})
	public ResponseEntity<Object> filterProductsByPrice(@PathVariable float startPrice
			, @PathVariable float endPrice
			, @RequestParam(defaultValue = "0", value = "page", required = false) int page
			, @RequestParam(defaultValue = "12", value = "size", required = false) int size) {
		return ResponseEntity.status(HttpStatus.FOUND).body(productService.filterProductByPrice(startPrice, endPrice, page, size));
	}
}
