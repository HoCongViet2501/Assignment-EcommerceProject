package com.assignment.springboot.service.impl;

import com.assignment.springboot.dto.responsedto.ProductDtoResponse;
import com.assignment.springboot.dto.responsedto.ProductsResponse;
import com.assignment.springboot.entity.Product;
import com.assignment.springboot.repository.BrandRepository;
import com.assignment.springboot.repository.CategoryRepository;
import com.assignment.springboot.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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
	
	private ProductsResponse productsResponse;
	private Product product;
	
	@BeforeEach
	public void setUp() {
		productRepository = mock(ProductRepository.class);
		productService = mock(ProductServiceImpl.class);
		modelMapper = mock(ModelMapper.class);
		brandRepository = mock(BrandRepository.class);
		categoryRepository = mock(CategoryRepository.class);
		productService = new ProductServiceImpl(productRepository, brandRepository, categoryRepository, modelMapper);
		productPage = mock(Page.class);
		productsResponse = mock(ProductsResponse.class);
		product = mock(Product.class);
	}
	
	@Test
	public void getProductByName_shouldReturnProductPaging_whenNameIsExist() {
		Pageable pageable = PageRequest.of(1, 10);
		ProductsResponse productsResponses = mock(ProductsResponse.class);
		mock(Product.class);
		Page<Product> productPage = mock(Page.class);
		when(productRepository.findProductsByNameContainingIgnoreCaseOrderByPriceAsc("name", pageable)).thenReturn(productPage);
		productsResponses = getContentPage(productPage);
		assertThat(productService.getProductsByName("name", 1, 10)).isEqualTo(productsResponses);
	}
	
	@Test
	public void getListProduct_shouldReturnListProductHavingPaging_andOrderByPriceASC() {
		Pageable pageable = mock(Pageable.class);
		pageable = PageRequest.of(1, 10);
		Page<Product> productPage = mock(Page.class);
		ProductsResponse productsResponses = mock(ProductsResponse.class);
		mock(Product.class);
		when(productRepository.findAllByOrderByPriceAsc(pageable)).thenReturn(productPage);
		productsResponses = getContentPage(productPage);
		verify(getContentPage(productPage));
		assertThat(productService.getAllProductOrderByPriceAsc(1, 10)).isEqualTo(productsResponses);
	}
	
	private ProductsResponse getContentPage(Page<Product> productPage) {
		mock(ProductDtoResponse.class);
		List<ProductDtoResponse> productDtoResponses = productPage.getContent().stream()
				.map(this::mapToDto).collect(Collectors.toList());
		return ProductsResponse.builder()
				.pageNo(productPage.getNumber())
				.pageSize(productPage.getSize())
				.totalElements(productPage.getNumberOfElements())
				.totalPages(productPage.getTotalPages())
				.last(productPage.isLast())
				.productDtos(productDtoResponses)
				.build();
	}
	
	private ProductDtoResponse mapToDto(Product product) {
		return this.modelMapper.map(product, ProductDtoResponse.class);
	}
}
