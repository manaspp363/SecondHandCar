package com.app.app.repository;

import com.app.app.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Iterator;
import java.util.List;

public interface ImageRepository extends JpaRepository<Image, Long> {
    List<Image> findByCarId(long carId);
    void deleteByCarId(long carId); // Should return void
}
