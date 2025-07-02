package com.app.app.repository;

import com.app.app.entity.Year;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface YearRepository extends JpaRepository<Year, Long> {

    Year findYearByYear(String name);
//    Year findYearById(Long id);
}