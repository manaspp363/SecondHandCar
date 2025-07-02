package com.app.app.controller;

import com.app.app.entity.Fuel;
import com.app.app.service.FuelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/fuel")
public class FuelController {

    private final FuelService fuelService;

    @Autowired
    public FuelController(FuelService fuelService) {
        this.fuelService = fuelService;
    }

    @PostMapping("/addFuel")
    public ResponseEntity<?> addFuel(@RequestBody Fuel fuel) {
        Fuel existingOrNewFuel = fuelService.addFuel(fuel);
        if (existingOrNewFuel == fuel) {
            return new ResponseEntity<>(existingOrNewFuel, HttpStatus.CREATED);
        } else if (existingOrNewFuel != null) {
            return new ResponseEntity<>(existingOrNewFuel, HttpStatus.ALREADY_REPORTED);
        }
        return new ResponseEntity<>("Failed to add fuel", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping("/deleteFuel")
    public ResponseEntity<?> deleteFuel(@RequestBody Fuel fuel) {
        Fuel deletedFuel = fuelService.deleteFuel(fuel);

        if (deletedFuel.getId() == null) {
            return new ResponseEntity<>("Fuel Not Found", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>("Fuel deleted successfully", HttpStatus.OK);
    }

    @GetMapping("/getFuelByName")
    public ResponseEntity<?> getFuel(@RequestParam String name) {
        Fuel fuels = fuelService.getFuelByName(name);

        if (fuels == null) {
            return new ResponseEntity<>("Fuel Not Found: " + name, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(fuels, HttpStatus.OK);
    }
}
