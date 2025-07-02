package com.app.app.controller;

import com.app.app.entity.Area;
import com.app.app.payload.AreaDto;
import com.app.app.service.AreaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/area")
public class AreaController {
    public AreaController(AreaService areaService) {
        this.areaService = areaService;
    }

    private AreaService areaService;

    @PostMapping("/addArea")
    public ResponseEntity<?> addArea(@RequestBody AreaDto areaDto) {
        AreaDto areaDto1 = areaService.addArea(areaDto);
        if (areaDto1 == areaDto) {
            return new ResponseEntity<>(areaDto, HttpStatus.ALREADY_REPORTED);
        } else if (areaDto1 != null) {
            return new ResponseEntity<>(areaDto1, HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Failed to add area", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping("/deleteArea")
    public ResponseEntity<?> deleteArea(@RequestBody AreaDto areaDto) {
        AreaDto areaDto1 = areaService.deleteArea(areaDto);
        if (areaDto1 == areaDto) {
            return new ResponseEntity<>(areaDto, HttpStatus.ALREADY_REPORTED);
        } else if (areaDto1 != null) {
            return new ResponseEntity<>(areaDto1, HttpStatus.ACCEPTED);

        }
        return new ResponseEntity<>("Failed to delete area", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/getArea")
    public ResponseEntity<?> getArea(@RequestParam long id) {
        Area area = areaService.getArea(id);
        if (area != null) {
            return new ResponseEntity<>(area, HttpStatus.OK);
        }
        return new ResponseEntity<>("Area not found", HttpStatus.NOT_FOUND);
    }

    @PutMapping("/updateArea")
    public ResponseEntity<?> updateArea(@RequestParam String pinCode, @RequestBody AreaDto areaDto) {
        AreaDto areaDto1 = areaService.updateArea(pinCode, areaDto);
        if (pinCode != areaDto1.getPinCode()) {
            return new ResponseEntity<>(areaDto1, HttpStatus.ACCEPTED);
        } else if (pinCode == areaDto1.getPinCode()) {
            return new ResponseEntity<>(areaDto1, HttpStatus.ALREADY_REPORTED);
        }
        return new ResponseEntity<>("Failed to update Aera.", HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping("/getAll")
    public ResponseEntity<?> getAllAreas() {
        return new ResponseEntity<>(areaService.getAllAreas(), HttpStatus.OK);
    }
}

