package com.assignment.springboot.controller.admin;

import com.assignment.springboot.dto.requestdto.ProductDtoRequest;
import com.assignment.springboot.dto.responsedto.ProductDtoResponse;
import com.assignment.springboot.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@PreAuthorize("hasAuthority('ADMIN')")
@RequestMapping("/api/admin/products")
public class ProductControllerAdmin {
	@Autowired
	private ProductService productService;
	
	@GetMapping("/{id}")
	@Operation(summary = "find product by id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "302", description = "found.the.product"),
			@ApiResponse(responseCode = "404", description = "not.found.product")
	})
	public ResponseEntity<ProductDtoResponse> findProductById(@PathVariable int id) {
		return ResponseEntity.status(HttpStatus.FOUND).body(this.productService.findProductById(id));
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@Operation(summary = "create new product")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "created.product"),
			@ApiResponse(responseCode = "400", description = "bad.request")
	})
	public ResponseEntity<ProductDtoResponse> createProduct(@Valid @RequestBody ProductDtoRequest productDtoRequest) {
		ProductDtoResponse productDtoResponse = this.productService.createProduct(productDtoRequest);
		return ResponseEntity.status(HttpStatus.CREATED).body(productDtoResponse);
	}
	
	@DeleteMapping("/{id}")
	@Operation(summary = "delete product by id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "deleted.the.product"),
			@ApiResponse(responseCode = "404", description = "not.found.product")
	})
	public ResponseEntity<String> deleteProduct(@PathVariable int id) {
		this.productService.deleteProduct(id);
		return ResponseEntity.ok().body("Delete.success.product.have.id." + id);
	}
	
	@PutMapping("/{id}")
	@Operation(summary = "update product")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "updated.product"),
			@ApiResponse(responseCode = "404", description = "not.found.product")
	})
	public ResponseEntity<ProductDtoResponse> updateProduct(@Valid @RequestBody ProductDtoRequest productDtoRequest, @PathVariable int id) {
		ProductDtoResponse productDtoResponse = this.productService.updateProduct(productDtoRequest, id);
		return ResponseEntity.ok().body(productDtoResponse);
	}
	
}
