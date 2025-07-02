package com.app.app.repository;

import com.app.app.entity.Area;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AreaRepository extends JpaRepository<Area, Long> {
    Area findAreaByPinCode(String pinCode);
}