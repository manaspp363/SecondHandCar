package com.app.app.repository;

import com.app.app.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {
    Brand findBrandByName(String name);
    Brand findBrandById(Long id);
}