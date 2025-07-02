package com.app.app.controller;

import com.app.app.entity.Car;
import com.app.app.payload.CarDto;
import com.app.app.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/car")
public class CarController {
    private CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }
    @PostMapping("/addCar")
    public ResponseEntity<Car> addCar(@RequestBody CarDto carDto){
        Car car1 = carService.saveCar(carDto);

        return new ResponseEntity<>(car1, HttpStatus.CREATED);
    }
    @GetMapping("/getAllCar")
    public ResponseEntity<List<Car>> getAllCar(){
        List<Car> carList = carService.getAllCars();
        return new ResponseEntity<>(carList, HttpStatus.OK);
    }
    @PutMapping("/updateCar")
    public ResponseEntity<Car> updateCar(@RequestParam Long id, @RequestBody CarDto carDto){
        Car car1 = carService.updateCar(id, carDto);
        return new ResponseEntity<>(car1, HttpStatus.OK);
    }
    @DeleteMapping("/deleteCar")
    public ResponseEntity<Car> deleteCar(@RequestParam Long id){
        Car car1 = carService.deleteCar(id);
        return new ResponseEntity<>(car1, HttpStatus.ACCEPTED);
    }
}
