package com.assignment.springboot.controller.admin;

import com.assignment.springboot.dto.request.BrandDtoRequest;
import com.assignment.springboot.dto.response.BrandDtoResponse;
import com.assignment.springboot.service.BrandService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
@Validated
@PreAuthorize("hasAuthority('ADMIN')")
@RequestMapping("/api/admin/brands")
public class BrandController {
    private final BrandService brandService;

    @Autowired
    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    //test
    @GetMapping
    @Operation(summary = "get all brand")
    public ResponseEntity<List<BrandDtoResponse>> getBrands() {
        return ResponseEntity.ok().body(brandService.getBrands());
    }

    @PostMapping
    @Operation(summary = "create new brand")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<BrandDtoResponse> createBrand(@Valid @RequestBody BrandDtoRequest brandDtoRequest) {
        BrandDtoResponse brandDtoResponse = this.brandService.createBrand(brandDtoRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(brandDtoResponse);
    }

    @GetMapping("/name")
    @Operation(summary = "get brand by name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "found.brand.by.name"),
            @ApiResponse(responseCode = "404", description = "not.found.brand")
    })
    public ResponseEntity<BrandDtoResponse> findBrandByName(@RequestParam String name) {
        BrandDtoResponse brandDtoResponse = this.brandService.findBrandByName(name);
        return ResponseEntity.ok().body(brandDtoResponse);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "delete brand by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "delete.success"),
            @ApiResponse(responseCode = "404", description = "not.found.brand")
    })
    public ResponseEntity<String> deleteBrand(@PathVariable long id) {
        brandService.deleteBrand(id);
        return ResponseEntity.ok().body("Delete success brand have id " + id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "update brand")
    public ResponseEntity<BrandDtoResponse> updateBrand(@RequestBody BrandDtoRequest brandDtoRequest, @PathVariable long id) {
        BrandDtoResponse brandDtoResponse = this.brandService.updateBrand(brandDtoRequest, id);
        return ResponseEntity.ok().body(brandDtoResponse);
    }
}
