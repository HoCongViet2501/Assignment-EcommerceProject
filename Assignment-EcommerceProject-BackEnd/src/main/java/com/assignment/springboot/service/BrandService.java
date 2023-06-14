package com.assignment.springboot.service;

import com.assignment.springboot.dto.request.BrandDtoRequest;
import com.assignment.springboot.dto.response.BrandDtoResponse;

import java.util.List;

public interface BrandService {
    List<BrandDtoResponse> getBrands();

    BrandDtoResponse createBrand(BrandDtoRequest brandDtoRequest);

    BrandDtoResponse findBrandByName(String name);

    void deleteBrand(long id);

    BrandDtoResponse updateBrand(BrandDtoRequest brandDto, long id);
}
