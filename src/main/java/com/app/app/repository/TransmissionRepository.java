package com.app.app.repository;

import com.app.app.entity.Transmission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransmissionRepository extends JpaRepository<Transmission, Long> {
    Transmission findTransmissionByName(String name);
//    Transmission findTransmissionById(Long id);
}