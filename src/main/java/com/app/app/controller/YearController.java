package com.app.app.controller;

import com.app.app.entity.Year;
import com.app.app.service.YearService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/year")
public class YearController {
    private YearService yearService;

    @Autowired
    public YearController(YearService yearService) {
        this.yearService = yearService;
    }

    @PostMapping("/addYear")
    public ResponseEntity<?> addYear(@RequestBody Year year) {
        Year existingOrNewYear = yearService.addYear(year);
        if (existingOrNewYear == year) {
            return new ResponseEntity<>(existingOrNewYear, HttpStatus.CREATED);
        } else if (existingOrNewYear != null) {
            return new ResponseEntity<>(existingOrNewYear, HttpStatus.ALREADY_REPORTED);
        }
        return new ResponseEntity<>("Failed to add year", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping("/deleteYear")
    public ResponseEntity<?> deleteYear(@RequestBody Year year) {
        Year deletedYear = yearService.deleteYear(year);
        if (deletedYear.getId() == null) {
            return new ResponseEntity<>("Year Not Found: " + year, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Year deleted successfully: " + deletedYear, HttpStatus.OK);
    }

    @GetMapping("/getYearByName")
    public ResponseEntity<?> getYear(@RequestParam String name) {
        Year years = yearService.getYear(name);
        if (years == null) {
            return new ResponseEntity<>("Year Not Found: " + name, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(years, HttpStatus.OK);
    }

    @PutMapping("/updateYear")
    public ResponseEntity<?> updateYear(@RequestParam String name, @RequestBody Year year) {
        Year updatedYear = yearService.updateYear(name, year);
        if (updatedYear != null) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body("Data Updated Successfully: " + updatedYear);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("There is no data available for the given Year: " + name);
        }
    }

    @GetMapping("/allYear")
    public ResponseEntity<?> getAllYear() {
        Iterable<Year> years = yearService.allYear();
        if (years.iterator().hasNext()) {
            return new ResponseEntity<>(years, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No Years Found", HttpStatus.NOT_FOUND);
        }
    }


}
