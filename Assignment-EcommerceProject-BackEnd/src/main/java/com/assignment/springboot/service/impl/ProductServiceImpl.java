package com.assignment.springboot.service.impl;

import com.assignment.springboot.dto.request.ProductDtoRequest;
import com.assignment.springboot.dto.response.ProductDtoResponse;
import com.assignment.springboot.dto.response.ProductsResponse;
import com.assignment.springboot.entity.Brand;
import com.assignment.springboot.entity.Category;
import com.assignment.springboot.entity.Product;
import com.assignment.springboot.exception.ResourceNotFoundException;
import com.assignment.springboot.repository.BrandRepository;
import com.assignment.springboot.repository.CategoryRepository;
import com.assignment.springboot.repository.ProductRepository;
import com.assignment.springboot.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {
	private final ProductRepository productRepository;
	private final BrandRepository brandRepository;
	private final CategoryRepository categoryRepository;
	private final ModelMapper modelMapper;
	
	@Autowired
	public ProductServiceImpl(ProductRepository productRepository, BrandRepository brandRepository, CategoryRepository categoryRepository, ModelMapper modelMapper) {
		this.productRepository = productRepository;
		this.brandRepository = brandRepository;
		this.categoryRepository = categoryRepository;
		this.modelMapper = modelMapper;
	}
	
	@Override
	public ProductsResponse getProductsByName(String productName, int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		Page<Product> productPage = this.productRepository.findProductsByNameContainingIgnoreCaseOrderByPriceAsc(productName, pageable);
		if (productPage.isEmpty()) {
			throw new ResourceNotFoundException("not.found.any.product");
		}
		return getContentPage(productPage);
	}
	
	@Override
	public ProductsResponse getAllProductOrderByPriceAsc(int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		Page<Product> productPage = this.productRepository.findAllByOrderByPriceAsc(pageable);
		return getContentPage(productPage);
	}
	
	@Override
	public ProductDtoResponse findProductById(long id) {
		Product product = this.productRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("Book.not.found.in.id: " + id));
		return mapToDto(product);
	}
	
	@Override
	public ProductsResponse getProductsByGender(String gender, int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		Page<Product> productPage = this.productRepository.findProductsByGenderOrderByPriceAsc(gender, pageable);
		if (productPage.isEmpty()) {
			throw new ResourceNotFoundException("not.found.product.have.gender." + gender);
		}
		return getContentPage(productPage);
	}
	
	@Override
	public ProductsResponse getProductsByBrandName(String brandName, int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		Page<Product> productPage = this.productRepository.findProductsByBrand_Name(brandName, pageable);
		if (productPage.isEmpty()) {
			throw new ResourceNotFoundException("not.found.product.have.brand.name." + brandName);
		}
		return getContentPage(productPage);
	}
	
	@Override
	public ProductsResponse getProductsByCategoryName(String categoryName, int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		Page<Product> productPage = this.productRepository.findProductsByCategory_Name(categoryName, pageable);
		if (productPage.isEmpty()) {
			throw new ResourceNotFoundException("not.found.product.have.category.name." + categoryName);
		}
		return getContentPage(productPage);
	}
	
	@Override
	public ProductDtoResponse createProduct(ProductDtoRequest productDtoRequest) {
		productDtoRequest.setCreatedDate(new Date());
		productDtoRequest.setUpdatedDate(new Date());
		Product product = this.productRepository.save(mapToEntity(productDtoRequest));
		return mapToDto(product);
	}
	
	
	@Override
	public void deleteProduct(long id) {
		Product product = this.productRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("Can't.find.product.have.id: " + id));
		this.productRepository.delete(product);
	}
	
	@Override
	public ProductDtoResponse updateProduct(ProductDtoRequest productDtoRequest, long id) {
		log.info("update.product.have.id " + id);
		Product product = this.productRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("Can't.find.product.have.id: " + id));
		modelMapper.map(productDtoRequest, product);
		product.setUpdatedDate(new Date());
		product.setId(id);
		this.productRepository.save(product);
		return mapToDto(product);
	}
	
	@Override
	public ProductsResponse filterProductByPrice(float startPrice, float endPrice, int page, int size) {
		Pageable pageable=PageRequest.of(page,size);
		Page<Product> productPage=this.productRepository.findProductByPriceBetweenOrderByPriceDesc(startPrice,endPrice,pageable);
		if (productPage.isEmpty()) {
			throw new ResourceNotFoundException("not.found.product.have.price.between."+startPrice+".and."+endPrice);
		}
		return getContentPage(productPage);
	}
	
	private ProductDtoResponse mapToDto(Product product) {
		return this.modelMapper.map(product, ProductDtoResponse.class);
	}
	
	private Product mapToEntity(ProductDtoRequest productDtoRequest) {
		Product product = modelMapper.map(productDtoRequest, Product.class);
		Brand brand = this.brandRepository.findById(productDtoRequest.getBrandId())
				.orElseThrow(() -> new ResourceNotFoundException("not.found.brand.id"));
		Category category = this.categoryRepository.findById(productDtoRequest.getCategoryId())
				.orElseThrow(() -> new ResourceNotFoundException("not.found.category.id"));
		product.setBrand(brand);
		product.setCategory(category);
		return product;
	}
	
	private ProductsResponse getContentPage(Page<Product> productPage) {
		List<ProductDtoResponse> productDtoResponses = productPage.getContent().stream()
				.map(this::mapToDto).collect(Collectors.toList());
		return ProductsResponse.builder().productDtos(productDtoResponses)
				.pageNo(productPage.getNumber())
				.pageSize(productPage.getSize())
				.totalElements(productPage.getNumberOfElements())
				.totalPages(productPage.getTotalPages())
				.last(productPage.isLast())
				.build();
	}
}
