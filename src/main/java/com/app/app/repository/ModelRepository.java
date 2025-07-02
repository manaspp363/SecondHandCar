package com.app.app.repository;

import com.app.app.entity.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModelRepository extends JpaRepository<Model, Long> {
//   Model findModelById(Long id);
   Model findModelByName(String name);
}