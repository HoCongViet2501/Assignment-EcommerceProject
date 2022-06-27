package com.assignment.springboot.controller;

import com.assignment.springboot.data.dto.BrandDTO;
import com.assignment.springboot.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
@Validated
@RequestMapping("/api/brands")
public class BrandController {
    private BrandService brandService;

    @Autowired
    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    //test
    @GetMapping
    public List<BrandDTO> getBrands() {
        return brandService.getAllBrands();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BrandDTO saveBrand(@Valid @RequestBody BrandDTO brandDTO) {
        return this.brandService.saveBrand(brandDTO);
    }

    @GetMapping("/name")
    public ResponseEntity<BrandDTO> findBrandByName(@RequestParam String name) {
        BrandDTO brandDTO = this.brandService.findBrandByName(name);
        return ResponseEntity.ok().body(brandDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBrand(@PathVariable int id) {
        brandService.deleteBrand(id);
        return ResponseEntity.ok().body("Delete success");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateBrand(@Valid @RequestBody BrandDTO brandDTO, @PathVariable int id) {
        this.brandService.updateBrand(brandDTO, id);
        return ResponseEntity.ok().body(brandDTO);
    }
}
