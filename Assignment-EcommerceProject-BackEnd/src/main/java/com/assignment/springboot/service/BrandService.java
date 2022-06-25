package com.assignment.springboot.service;

import com.assignment.springboot.data.dto.BrandDTO;
import com.assignment.springboot.data.entity.Brand;

import java.util.List;

public interface BrandService {
    List<BrandDTO> getAllBrands();
    BrandDTO saveBrand(BrandDTO brand);
    BrandDTO findBrandByName(String name);
    void deleteBrand(int id);
    BrandDTO updateBrand(BrandDTO brandDTO,int id);
}
