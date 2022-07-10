package com.assignment.springboot.service;

import com.assignment.springboot.data.dto.BrandDTO;
import com.assignment.springboot.data.entity.Brand;
import com.assignment.springboot.repository.BrandRepository;
import com.assignment.springboot.service.impl.BrandServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.modelmapper.ModelMapper;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith({MockitoExtension.class})
@MockitoSettings(strictness = Strictness.LENIENT)
public class TestBrandService {
	private BrandRepository brandRepository;
	
	private BrandServiceImpl brandService;
	
	private BrandDTO brandDto;
	ModelMapper modelMapper;
	private Brand brand;
	
	@BeforeEach
	public void setUp() {
		modelMapper = mock(ModelMapper.class);
		brandRepository = mock(BrandRepository.class);
		brandService = new BrandServiceImpl(brandRepository, modelMapper);
		brandDto = mock(BrandDTO.class);
		brand = mock(Brand.class);
		brandDto.setId(0);
		brandDto.setAddress("test address");
		brandDto.setDescription("test desc");
		brandDto.setPhoneNumber("test phone");
		brandDto.setName("test name");
		when(modelMapper.map(brandDto, Brand.class)).thenReturn(brand);
		when(modelMapper.map(brand, BrandDTO.class)).thenReturn(brandDto);
	}
	
	@DisplayName("junit test for createBrand method")
	@Test
	public void createBrand_shouldReturnNewBrand() {
		when(brandRepository.save(brand)).thenReturn(brand);
		assertThat(brandDto).isEqualTo(brandService.createBrand(brandDto));
	}
	
	@DisplayName("junit test for getBrandByName method")
	@Test
	public void getByName_shouldReturnBrand_whenBrandNameExist() {
		String name = brandDto.getName();
		when(brandRepository.findBrandByName(name)).thenReturn(brand);
		assertThat(brandDto).isEqualTo(brandService.findBrandByName(name));
	}
	
	@DisplayName("junit test for updateBrand method")
	@Test
	public void updateBrand_shouldReturnUpdatedBrand() {
		Optional<Brand> optionalBrand = Optional.of(brand);
		when(brandRepository.findById(anyInt())).thenReturn(Optional.of(brand));
		brandDto = modelMapper.map(optionalBrand.get(), BrandDTO.class);
		when(brandRepository.save(optionalBrand.get())).thenReturn(brand);
		assertThat(brandService.updateBrand(brandDto, brandDto.getId())).isEqualTo(brandDto);
	}
}
