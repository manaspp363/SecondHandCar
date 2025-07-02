package com.app.app.controller;


import com.app.app.entity.Brand;
import com.app.app.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/brand")
public class BrandController {
    @Autowired
    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    private BrandService brandService;

// Constructor-based Dependency Injection


    @PostMapping("/addBrand")
    public ResponseEntity<?> addBrand(@RequestBody Brand brand) {
        Brand existingOrNewBrand = brandService.addBrand(brand);
        if (existingOrNewBrand == brand) {
            return new ResponseEntity<>(existingOrNewBrand, HttpStatus.CREATED);
        } else if (existingOrNewBrand != null) {
            return new ResponseEntity<>(existingOrNewBrand, HttpStatus.ALREADY_REPORTED);
        }
        return new ResponseEntity<>("Failed to add brand", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping("/deleteBrand")
    public ResponseEntity<?> deleteBrand(@RequestBody Brand brand) {
        Brand deletedBrand = brandService.deleteBrand(brand);

        if (deletedBrand.getId() == null) {
            return new ResponseEntity<>("Brand Not Found: " + brand, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>("Brand deleted successfully: " + deletedBrand, HttpStatus.OK);
    }

    @GetMapping("/getBrandByName")
    public ResponseEntity<?> getBrand(@RequestParam String name) {
        Brand brands = brandService.getBrand(name);

        if (brands == null) {
            return new ResponseEntity<>("Brand Not Found: " + name, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(brands, HttpStatus.OK);
    }

}
