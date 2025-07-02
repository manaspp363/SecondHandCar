package com.app.app.service;

import com.app.app.entity.Brand;
import com.app.app.repository.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class BrandService {
    private final BrandRepository brandRepository;

    @Autowired
    public BrandService(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }


    public Brand addBrand(Brand brand) {
        Brand cheack = brandRepository.findBrandByName(brand.getName());
        if (cheack == null) {
            return brandRepository.save(brand);
        } else {
            return cheack;
        }
    }


    public Brand deleteBrand(Brand brand) {
        Brand cheack = brandRepository.findBrandByName(brand.getName());
        Brand brand2 = new Brand();
        if (cheack != null) {
            brand2.setId(cheack.getId());
            brand2.setName(cheack.getName());
            brandRepository.deleteById(cheack.getId());
            return brand2;

        } else {
            return brand;
        }

    }

    public Brand getBrand(String brand) {
        return brandRepository.findBrandByName(brand);
    }


}
