package com.assignment.springboot.controller;

import com.assignment.springboot.data.entity.Brand;
import com.assignment.springboot.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/brands")
public class BrandController {
    private BrandService brandService;

    @Autowired
    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }
    //test
    @GetMapping
    public List<Brand> getBrands()  {
        return brandService.getAllBrands();
    }
    @PostMapping
    public Brand addBrand(@RequestBody Brand brand){
        return this.brandService.addBrand(brand);
    }
}
