package com.app.app.controller;

import com.app.app.entity.Car;
import com.app.app.entity.Image;
import com.app.app.repository.CarRepository;
import com.app.app.repository.ImageRepository;
import com.app.app.service.BucketService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/images")
public class ImageController {
    private BucketService bucketService;
    private CarRepository carRepository;
    private ImageRepository imageRepository;


    public ImageController(BucketService bucketService,
                           CarRepository carRepository,
                           ImageRepository imageRepository) {
        this.bucketService = bucketService;
        this.imageRepository = imageRepository;
        this.carRepository = carRepository;
    }

    @PostMapping(path = "/upload/file/{bucketName}/car/{carId}")
    public ResponseEntity<Image> uploadImage(@RequestParam MultipartFile file, @PathVariable String bucketName, @PathVariable long carId) {

        String url = bucketService.uploadFile(file, bucketName, carId);
        Car car = carRepository.findById(carId).get();
        Image image = new Image();
        image.setUrl(url);
        image.setCar(car);
        imageRepository.save(image);
        return new ResponseEntity<>(image, HttpStatus.CREATED);
    }

}
