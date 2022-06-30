package com.assignment.springboot.service.impl;

import com.assignment.springboot.data.dto.BrandDTO;
import com.assignment.springboot.data.entity.Brand;
import com.assignment.springboot.exception.ResourceNotFoundException;
import com.assignment.springboot.repository.BrandRepository;
import com.assignment.springboot.service.BrandService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
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
    public List<BrandDTO> getBrands() {
        List<Brand> brands = this.brandRepository.findAll();
        if (brands.isEmpty()) {
            throw new ResourceNotFoundException("Can't.get.list.brands");
        }
        return brands.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    private BrandDTO mapToDto(Brand brand) {
        return this.mapper.map(brand, BrandDTO.class);
    }

    @Override
    public BrandDTO createBrand(BrandDTO brandDto) {
        Brand brand = mapper.map(brandDto, Brand.class);
        this.brandRepository.save(brand);
        return brandDto;
    }

    @Override
    public BrandDTO findBrandByName(String name) {
        log.info("find.brand.by.name.of.brand ");
        BrandDTO brandDto = mapper.map(this.brandRepository.findBrandByName(name), BrandDTO.class);
        if (brandDto == null) {
            throw new ResourceNotFoundException("Can't.find.brand.have.name " + name);
        }
        return brandDto;
    }

    @Override
    public void deleteBrand(int id) {
        Brand brand = this.brandRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Can't.find.brand.have.id: " + id));
        this.brandRepository.delete(brand);
    }

    @Override
    public void updateBrand(BrandDTO brandDto, int id) {
        Optional<Brand> brandOptional = this.brandRepository.findById(id);
        if (brandOptional.isEmpty()) {
            throw new ResourceNotFoundException("can't.find.brand.have.id: " + id);
        }
        Brand brand = brandOptional.get();
        mapper.map(brandDto, brand);
        this.brandRepository.save(brand);
    }
}
