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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public List<BrandDTO> getAllBrands() {
        log.info("get all brands from database");
        List<Brand> brands = this.brandRepository.findAll();
        if (!brands.isEmpty()) {
            List<BrandDTO> brandDTOS = new ArrayList<>();
            for (Brand brand : brands) {
                brandDTOS.add(mapper.map(brand, BrandDTO.class));
            }
            return brandDTOS;
        }
        throw new ResourceNotFoundException("Can't get all brands");
    }

    @Override
    public BrandDTO saveBrand(BrandDTO brandDTO) {
        log.info("save new brand to database");
        Brand brand = mapper.map(brandDTO, Brand.class);
        Brand brandSave = this.brandRepository.save(brand);
        if (brandSave != null) {
            return brandDTO;
        }
        throw new ResourceNotFoundException("Can't save brand to database");
    }

    @Override
    public BrandDTO findBrandByName(String name) {
        log.info("find brand by name of brand ");
        Brand brand = this.brandRepository.findBrandByName(name);
        BrandDTO brandDTO = mapper.map(brand, BrandDTO.class);
        if (brand != null) {
            return brandDTO;
        }
        throw new ResourceNotFoundException("Can't find brand have name " + name);
    }

    @Override
    public void deleteBrand(int id) {
        log.info("delete brand by id of brand ");
        Brand brand = this.brandRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Can't find brand have id" + id));
        this.brandRepository.delete(brand);
    }

    @Override
    public BrandDTO updateBrand(BrandDTO brandDTO,int id) {
        Optional<Brand> brandOptional=this.brandRepository.findById(id);
        if(brandOptional.isPresent()){
            Brand brand=brandOptional.get();
            mapper.map(brandDTO,brand);
            this.brandRepository.save(brand);
            return brandDTO;
        }
        throw new ResourceNotFoundException("Can't update brand");
    }

}
