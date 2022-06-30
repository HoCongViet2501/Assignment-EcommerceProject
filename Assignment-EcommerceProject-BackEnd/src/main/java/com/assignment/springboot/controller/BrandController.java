package com.assignment.springboot.controller;

import com.assignment.springboot.data.dto.BrandDTO;
import com.assignment.springboot.service.BrandService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    private final BrandService brandService;

    @Autowired
    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    //test
    @GetMapping
    @Operation(summary = "get all brand")
    public List<BrandDTO> getBrands() {
        return brandService.getBrands();
    }

    @PostMapping
    @Operation(summary = "create new brand")
    @ResponseStatus(HttpStatus.CREATED)
    public BrandDTO createBrand(@Valid @RequestBody BrandDTO brandDTO) {
        return this.brandService.createBrand(brandDTO);
    }

    @GetMapping("/name")
    @Operation(summary = "get brand by name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "found.brand.by.name"),
            @ApiResponse(responseCode = "404", description = "not.found.brand")
    })
    public ResponseEntity<BrandDTO> findBrandByName(@RequestParam String name) {
        BrandDTO brandDTO = this.brandService.findBrandByName(name);
        return ResponseEntity.ok().body(brandDTO);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "delete brand by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "delete.success"),
            @ApiResponse(responseCode = "404", description = "not.found.brand")
    })
    public ResponseEntity<String> deleteBrand(@PathVariable int id) {
        brandService.deleteBrand(id);
        return ResponseEntity.ok().body("Delete.success.brand.have.id " + id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "update brand")
    public ResponseEntity<Object> updateBrand(@Valid @RequestBody BrandDTO brandDTO, @PathVariable int id) {
        this.brandService.updateBrand(brandDTO, id);
        return ResponseEntity.ok().body(brandDTO);
    }
}
