package com.app.app.controller;

import com.app.app.entity.Transmission;
import com.app.app.service.TransmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/transmission")


public class TransmissionController {

    private TransmissionService transmissionService;

    @Autowired
    public TransmissionController(TransmissionService transmissionService) {
        this.transmissionService = transmissionService;
    }

    @PostMapping("/addTransmission")
    public ResponseEntity<?> addTransmission(@RequestBody Transmission transmission) {
        Transmission existingOrNewTransmission = transmissionService.addTransmission(transmission);
        if (existingOrNewTransmission == transmission) {
            return new ResponseEntity<>(existingOrNewTransmission, HttpStatus.CREATED);
        } else if (existingOrNewTransmission != null) {
            return new ResponseEntity<>(existingOrNewTransmission, HttpStatus.ALREADY_REPORTED);
        }
        return new ResponseEntity<>("Failed to add transmission", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping("/deleteTransmission")
    public ResponseEntity<?> deleteTransmission(@RequestBody Transmission transmission) {
        Transmission deletedTransmission = transmissionService.deleteTransmission(transmission);

        if (deletedTransmission.getId() == null) {
            return new ResponseEntity<>("Transmission Not Found: " + transmission, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>("Transmission deleted successfully: " + deletedTransmission, HttpStatus.OK);
    }

    @GetMapping("/getTransmissionByName")
    public ResponseEntity<?> getTransmission(@RequestParam String name) {
        Transmission transmissions = transmissionService.getTransmission(name);

        if (transmissions == null) {
            return new ResponseEntity<>("Transmission Not Found: " + name, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(transmissions, HttpStatus.OK);
    }

}
