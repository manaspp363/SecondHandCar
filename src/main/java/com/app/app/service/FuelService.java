package com.app.app.service;

import com.app.app.entity.Fuel;
import com.app.app.entity.Model;
import com.app.app.repository.FuelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FuelService {
    private FuelRepository fuelRepository;

    @Autowired
    public FuelService(FuelRepository fuelRepository) {
        this.fuelRepository = fuelRepository;
    }

    public Fuel addFuel(Fuel fuel) {
        Fuel cheack = fuelRepository.findFuelByName(fuel.getName());
        if (cheack == null) {
            return fuelRepository.save(fuel);
        } else {
            return cheack;
        }
    }

    public Fuel deleteFuel(Fuel fuel) {
        Fuel cheack = fuelRepository.findFuelByName(fuel.getName());
        if (cheack != null) {
            Fuel fuelReturn = new Fuel();
            fuelReturn.setId(cheack.getId());
            fuelReturn.setName(cheack.getName());
            fuelRepository.deleteById(cheack.getId());
            return fuelReturn;
        } else {
            return fuel;
        }
    }

    public Fuel getFuelByName(String name) {
        return fuelRepository.findFuelByName(name);
    }
}
