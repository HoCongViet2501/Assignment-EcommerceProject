package com.assignment.springboot.service.impl;

import com.assignment.springboot.dto.response.ProductDtoResponse;
import com.assignment.springboot.dto.response.ProductsPagingResponse;
import com.assignment.springboot.entity.Product;
import com.assignment.springboot.repository.BrandRepository;
import com.assignment.springboot.repository.CategoryRepository;
import com.assignment.springboot.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class TestProductServiceImpl {
	private ProductServiceImpl productService;
	private ProductRepository productRepository;
	private ModelMapper modelMapper;
	
	private BrandRepository brandRepository;
	
	private CategoryRepository categoryRepository;
	
	private Page<Product> productPage;
	
	private ProductsPagingResponse productsResponse;
	
	@BeforeEach
	public void setUp() {
		productRepository = mock(ProductRepository.class);
		modelMapper = mock(ModelMapper.class);
		brandRepository = mock(BrandRepository.class);
		categoryRepository = mock(CategoryRepository.class);
		productService = new ProductServiceImpl(productRepository, brandRepository, categoryRepository, modelMapper);
	}
	
	@Test
	public void getProductByName_shouldReturnProductPaging_whenNameIsExist() {
		productPage = mock(Page.class);
		when(productRepository.findProductsByNameContainingIgnoreCaseOrderByPriceAsc(eq("name"), any(Pageable.class))).thenReturn(productPage);
		Product product1 = mock(Product.class);
		Product product2 = mock(Product.class);
		ProductDtoResponse productDtoResponse1 = mock(ProductDtoResponse.class);
		ProductDtoResponse productDtoResponse2 = mock(ProductDtoResponse.class);
		when(productPage.getContent()).thenReturn(List.of(product1, product2));
		when(modelMapper.map(product1, ProductDtoResponse.class)).thenReturn(productDtoResponse1);
		when(modelMapper.map(product2, ProductDtoResponse.class)).thenReturn(productDtoResponse2);
		when(productPage.getTotalPages()).thenReturn(10);
		ArgumentCaptor<Pageable> captor;
		ProductsPagingResponse actual = productService.getProductsByName("name", 1, 10);
		
		assertThat(actual.getTotalPages()).isEqualTo(10);
		assertThat(actual.getProductDtos().get(0)).isEqualTo(productDtoResponse1);
		assertThat(actual.getProductDtos().get(1)).isEqualTo(productDtoResponse2);
	}
	
	@Test
	public void getListProduct_shouldReturnListProductHavingPaging_andOrderByPriceASC() {
		productPage = mock(Page.class);
		Pageable pageable = PageRequest.of(1, 10);
		ProductDtoResponse productDtoResponse = mock(ProductDtoResponse.class);
		List<ProductDtoResponse> productDtoResponses = mock(List.class);
		when(productRepository.findAllByOrderByPriceAsc(pageable)).thenReturn(productPage);
		when(productPage.getContent().stream().map(product -> modelMapper.map(product, ProductDtoResponse.class))
				.collect(Collectors.toList()))
				.thenReturn(productDtoResponses);
		assertThat(productService.getAllProductOrderByPriceAsc(1, 10).getPageSize()).isEqualTo(productsResponse.getPageSize());
	}
	
}
