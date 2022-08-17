package com.assignment.springboot.service;

import com.assignment.springboot.dto.request.ProductDtoRequest;
import com.assignment.springboot.dto.response.ProductDtoResponse;
import com.assignment.springboot.dto.response.ProductsPagingResponse;

public interface ProductService {
	ProductsPagingResponse getProductsByName(String productName, int page, int size);
	
	ProductsPagingResponse getAllProductOrderByPriceAsc(int page, int size);
	
	ProductDtoResponse findProductById(long id);
	
	ProductsPagingResponse getProductsByGender(String gender, int page, int size);
	
	ProductsPagingResponse getProductsByBrandName(String brandName, int page, int size);
	
	ProductsPagingResponse getProductsByCategoryName(String categoryName, int page, int size);
	
	ProductDtoResponse createProduct(ProductDtoRequest productDtoRequest);
	
	void deleteProduct(long id);
	
	ProductDtoResponse updateProduct(ProductDtoRequest productDtoRequest, long id);
	
	ProductsPagingResponse filterProductByPrice(float startPrice, float endPrice, int page, int size);
}
