package com.assignment.springboot.service;

import com.assignment.springboot.dto.request.ProductDtoRequest;
import com.assignment.springboot.dto.response.ProductDtoResponse;
import com.assignment.springboot.dto.response.ProductsResponse;

public interface ProductService {
	ProductsResponse getProductsByName(String productName, int page, int size);
	
	ProductsResponse getAllProductOrderByPriceAsc(int page, int size);
	
	ProductDtoResponse findProductById(long id);
	
	ProductsResponse getProductsByGender(String gender, int page, int size);
	
	ProductsResponse getProductsByBrandName(String brandName, int page, int size);
	
	ProductsResponse getProductsByCategoryName(String categoryName, int page, int size);
	
	ProductDtoResponse createProduct(ProductDtoRequest productDtoRequest);
	
	void deleteProduct(long id);
	
	ProductDtoResponse updateProduct(ProductDtoRequest productDtoRequest, long id);
	
	ProductsResponse filterProductByPrice(float startPrice, float endPrice, int page, int size);
}
