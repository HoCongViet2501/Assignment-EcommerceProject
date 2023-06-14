package com.assignment.springboot.service.impl;

import com.assignment.springboot.dto.request.BrandDtoRequest;
import com.assignment.springboot.dto.response.BrandDtoResponse;
import com.assignment.springboot.entity.Brand;
import com.assignment.springboot.exceptions.ResourceNotFoundException;
import com.assignment.springboot.repository.BrandRepository;
import com.assignment.springboot.service.BrandService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class BrandServiceImpl implements BrandService {
	private final BrandRepository brandRepository;
	private final ModelMapper mapper;
	
	@Autowired
	public BrandServiceImpl(BrandRepository brandRepository, ModelMapper mapper) {
		this.brandRepository = brandRepository;
		this.mapper = mapper;
	}
	
	@Override
	public List<BrandDtoResponse> getBrands() {
		List<Brand> brands = this.brandRepository.findAll();
		if (brands.isEmpty()) {
			throw new ResourceNotFoundException("Can't.get.list.brands");
		}
		return brands.stream().map(this::mapToDto).collect(Collectors.toList());
	}
	
	private BrandDtoResponse mapToDto(Brand brand) {
		return this.mapper.map(brand, BrandDtoResponse.class);
	}
	
	@Override
	public BrandDtoResponse createBrand(BrandDtoRequest brandDtoRequest) {
		Brand brand = mapper.map(brandDtoRequest, Brand.class);
		this.brandRepository.save(brand);
		return mapToDto(brand);
	}
	
	@Override
	public BrandDtoResponse findBrandByName(String name) {
		log.info("find.brand.by.name.of.brand ");
		Brand brand = this.brandRepository.findBrandByName(name);
		BrandDtoResponse brandDtoResponse = mapper.map(brand, BrandDtoResponse.class);
		if (brandDtoResponse == null) {
			throw new ResourceNotFoundException("Can't.find.brand.have.name " + name);
		}
		return brandDtoResponse;
	}
	
	@Override
	public void deleteBrand(long id) {
		Brand brand = this.brandRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("Can't.find.brand.have.id: " + id));
		this.brandRepository.delete(brand);
	}
	
	@Override
	public BrandDtoResponse updateBrand(BrandDtoRequest brandDtoRequest, long id) {
		Brand brand = this.brandRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("can't.find.brand.have.id: " + id));
		mapper.map(brandDtoRequest, brand);
		this.brandRepository.save(brand);
		return mapper.map(brand, BrandDtoResponse.class);
	}
}
