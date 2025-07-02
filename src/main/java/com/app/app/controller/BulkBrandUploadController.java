package com.app.app.controller;

import com.app.app.entity.Brand;
import com.app.app.repository.BrandRepository;
import com.app.app.service.BulkBrandUploadService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/api/v1/bulk-car")
public class BulkBrandUploadController {

    private final BulkBrandUploadService bulkBrandUploadService;
    private final BrandRepository brandRepository;

    public BulkBrandUploadController(BulkBrandUploadService bulkBrandUploadService, BrandRepository brandRepository) {
        this.bulkBrandUploadService = bulkBrandUploadService;
        this.brandRepository = brandRepository;
    }

    @PostMapping("/brand-upload")
    public ResponseEntity<String> uploadCarNames() {
        List<Brand> brands = bulkBrandUploadService.carNameUpload(); // Get brands from service layer

        if (brands.isEmpty()) {
            return ResponseEntity.badRequest().body("No valid data found in the file.");
        }

        brandRepository.saveAll(brands); // Save all brands in bulk
        return ResponseEntity.ok("Successfully uploaded" + brands.size() + " brand records.");
    }


}