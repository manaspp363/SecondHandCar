package com.app.app.repository;

import com.app.app.payload.CarDto;
import com.app.app.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long> {
    @Query("""
            SELECT c FROM Car c\s
                JOIN c.brand b\s
                JOIN c.fuel f\s
                JOIN c.model m\s
                JOIN c.transmission t\s
                JOIN c.year y\s
                WHERE CAST(c.price AS string) LIKE CONCAT('%', :param, '%')
                   OR LOWER(b.name) LIKE LOWER(CONCAT('%', :param, '%'))\s
                   OR LOWER(f.name) LIKE LOWER(CONCAT('%', :param, '%'))\s
                   OR LOWER(m.name) LIKE LOWER(CONCAT('%', :param, '%'))\s
                   OR LOWER(t.name) LIKE LOWER(CONCAT('%', :param, '%'))\s
                   OR LOWER(y.year) LIKE LOWER(CONCAT('%', :param, '%'))\s""")
    List<Car> findByParam(@Param("param") String param);
}
