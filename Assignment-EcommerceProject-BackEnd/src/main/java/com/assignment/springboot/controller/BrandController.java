package com.assignment.springboot.controller;

import com.assignment.springboot.data.dto.BrandDTO;
import com.assignment.springboot.exception.ResourceNotFoundException;
import com.assignment.springboot.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/brands")
public class BrandController {
    private  BrandService brandService;

    @Autowired
    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }
    //test
    @GetMapping
    public List<BrandDTO> getBrands()  {
        return brandService.getAllBrands();
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BrandDTO saveBrand(@RequestBody BrandDTO brandDTO){
        return this.brandService.saveBrand(brandDTO);
    }
    @GetMapping("/name")
    public BrandDTO findBrandByName(@RequestParam String name){
        return this.brandService.findBrandByName(name);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBrand(@PathVariable int id){
        try {
           brandService.deleteBrand(id);
            return  ResponseEntity.ok().body("Delete success");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("Can't delete/find brand have id = " + id);
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateBrand(@RequestBody BrandDTO brandDTO,@PathVariable int id){
        try {
            this.brandService.updateBrand(brandDTO,id);
            return ResponseEntity.ok().body(brandDTO);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("Can't update brand have id="+id);

        }
    }
}
