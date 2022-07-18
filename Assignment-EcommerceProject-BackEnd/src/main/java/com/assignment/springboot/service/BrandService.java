package com.assignment.springboot.service;

import com.assignment.springboot.dto.requestdto.BrandDtoRequest;
import com.assignment.springboot.dto.responsedto.BrandDtoResponse;

import java.util.List;

public interface BrandService {
    List<BrandDtoResponse> getBrands();

    BrandDtoResponse createBrand(BrandDtoRequest brandDtoRequest);

    BrandDtoResponse findBrandByName(String name);

    void deleteBrand(long id);

    BrandDtoResponse updateBrand(BrandDtoRequest brandDto, long id);
}
