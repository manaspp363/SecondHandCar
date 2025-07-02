package com.app.app.controller;

import com.app.app.entity.Car;
import com.app.app.service.CarService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/searchCar")
public class searchCarController {
    private CarService carService;

    public searchCarController(CarService carService) {
        this.carService = carService;
    }


    @GetMapping("/getByParam")
    public ResponseEntity<List<Car>> getByParam(@RequestParam String param){
        List<Car> carList = carService.getAllByParam(param);
        return new ResponseEntity<>(carList, HttpStatus.OK);
    }
}
