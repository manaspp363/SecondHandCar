//package com.app.app.controller;
//
//import com.app.app.entity.Car;
//import com.app.app.entity.Image;
//import com.app.app.repository.CarRepository;
//import com.app.app.service.BucketService;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/v1/imagesByAgent")
//public class ActualCarPhotos {
//    private BucketService bucketService;
//
//
//    public ActualCarPhotos(CarRepository carRepository) {
//        this.carRepository = carRepository;
//    }
//
//    private CarRepository carRepository;
//
//    public ActualCarPhotos(BucketService bucketService) {
//        this.bucketService = bucketService;
//    }
//
//    @PostMapping(path = "/upload/file/{bucketName}/car/{carId}")
//    public ResponseEntity<Image> uploadImage(@RequestParam List<MultipartFile> files, @PathVariable String bucketName, @PathVariable long carId) {
//        for (MultipartFile  file : files) {
//            bucketService.uploadFile(file, bucketName, carId);
//        }
//        return null;
//    }
//}