package com.app.app.repository;

import com.app.app.entity.Costumervisit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CostumervisitRepository extends JpaRepository<Costumervisit, Long> {
    Costumervisit findByEmail(String email);
    Costumervisit findByMobile(String mobile);
}