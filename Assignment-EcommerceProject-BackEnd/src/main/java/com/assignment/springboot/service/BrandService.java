package com.assignment.springboot.service;

import com.assignment.springboot.data.dto.BrandDTO;

import java.util.List;

public interface BrandService {
    List<BrandDTO> getBrands();

    BrandDTO createBrand(BrandDTO brand);

    BrandDTO findBrandByName(String name);

    void deleteBrand(int id);

    BrandDTO updateBrand(BrandDTO brandDTO, int id);
}
