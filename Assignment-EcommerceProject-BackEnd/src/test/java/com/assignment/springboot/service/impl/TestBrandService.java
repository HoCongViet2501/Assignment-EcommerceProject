//package com.assignment.springboot.service.impl;
//
//
//import com.assignment.springboot.dto.request.BrandDtoRequest;
//import com.assignment.springboot.dto.response.BrandDtoResponse;
//import com.assignment.springboot.entity.Brand;
//import com.assignment.springboot.repository.BrandRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.modelmapper.ModelMapper;
//
//import java.util.Optional;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.mockito.Mockito.*;
//
//
//public class TestBrandService {
//	private BrandRepository brandRepository;
//
//	private BrandServiceImpl brandServiceImpl;
//
//	private BrandDtoResponse brandDtoResponse;
//	private BrandDtoRequest brandDtoRequest;
//	ModelMapper modelMapper;
//	private Brand brand;
//
//	@BeforeEach
//	public void setUp() {
//		modelMapper = mock(ModelMapper.class);
//		brandDtoRequest = mock(BrandDtoRequest.class);
//		brandRepository = mock(BrandRepository.class);
//		brandServiceImpl = new BrandServiceImpl(brandRepository, modelMapper);
//		brandDtoResponse = mock(BrandDtoResponse.class);
//		brand = mock(Brand.class);
//		when(modelMapper.map(brandDtoResponse, Brand.class)).thenReturn(brand);
//	}
//
//	@DisplayName("junit test for createBrand method")
//	@Test
//	public void createBrand_shouldReturnNewBrand() {
//		when(modelMapper.map(brandDtoRequest, Brand.class)).thenReturn(brand);
//		when(brandRepository.save(brand)).thenReturn(brand);
//		when(modelMapper.map(brand,BrandDtoResponse.class)).thenReturn(brandDtoResponse);
//		assertThat(brandServiceImpl.createBrand(brandDtoRequest)).isEqualTo(brandDtoResponse);
//	}
//
//	@DisplayName("junit test for getBrandByName method")
//	@Test
//	public void getByName_shouldReturnBrand_whenBrandNameExist() {
//		when(brandRepository.findBrandByName("name")).thenReturn(brand);
//		when(modelMapper.map(brand,BrandDtoResponse.class)).thenReturn(brandDtoResponse);
//		assertThat(brandServiceImpl.findBrandByName("name")).isEqualTo(brandDtoResponse);
//	}
//
//	@DisplayName("junit test for updateBrand method")
//	@Test
//	public void updateBrand_shouldReturnUpdatedBrand_whenUpdatedSuccess() {
//		when(brandRepository.findById(1L)).thenReturn(Optional.of(brand));
//		when(modelMapper.map(brand, BrandDtoResponse.class)).thenReturn(brandDtoResponse);
//		when(brandRepository.save(brand)).thenReturn(brand);
//		BrandDtoResponse actual = brandServiceImpl.updateBrand(brandDtoRequest, 1L);
//		assertThat(actual).isEqualTo(brandDtoResponse);
//	}
//}
