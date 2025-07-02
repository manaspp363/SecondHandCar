package com.app.app.service;

import com.app.app.entity.Year;
import com.app.app.repository.YearRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authorization.method.AuthorizeReturnObject;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class YearService {

    private final YearRepository yearRepository;

    @Autowired
    public YearService(YearRepository yearRepository) {
        this.yearRepository = yearRepository;
    }

    public Year addYear(Year year) {
        Year check = yearRepository.findYearByYear(year.getYear());
        if (check == null) {
            return yearRepository.save(year);
        } else {
            return check;
        }
    }

    public Year deleteYear(Year year) {
        Year check = yearRepository.findYearByYear(year.getYear());
        Year year2 = new Year();
        if (check != null) {
            year2.setId(check.getId());
            year2.setYear(check.getYear());
            yearRepository.deleteById(check.getId());
            return year2;
        } else {
            return year;
        }
    }

    public Year getYear(String year) {
        return yearRepository.findYearByYear(year);
    }

    public Year updateYear(String name, Year year) {
        Optional<Year> yearOp = Optional.ofNullable(yearRepository.findYearByYear(name));

        if (yearOp.isPresent()) {
            Year existingYear = yearOp.get();
            existingYear.setYear(year.getYear());  // Updating year value
            return yearRepository.save(existingYear);
        } else {
            return null; // If not found, return null
        }
    }
    public Iterable<Year> allYear(){
        return yearRepository.findAll();
    }
}


