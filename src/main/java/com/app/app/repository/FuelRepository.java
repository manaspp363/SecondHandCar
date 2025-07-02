package com.app.app.repository;

import com.app.app.entity.Fuel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FuelRepository extends JpaRepository<Fuel, Long> {
    Fuel findFuelByName(String name);
//    Fuel findFuelById(Long id);
}