package com.app.app.service;

import com.app.app.entity.Car;
import com.app.app.entity.Image;
import com.app.app.repository.ImageRepository;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

@Service
public class ImageService {
    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }
    private ImageRepository imageRepository;

    public boolean deleteImage(Car car) {
        List<Image> images = imageRepository.findByCarId(car.getId());
        if (!images.isEmpty()) {
            imageRepository.deleteByCarId(car.getId());  // Delete by car ID, not Image
            return true;
        }
        return false;  // Return false if no images found
    }


}
