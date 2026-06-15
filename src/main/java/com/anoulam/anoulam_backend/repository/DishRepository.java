package com.anoulam.anoulam_backend.repository;

import com.anoulam.anoulam_backend.model.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DishRepository extends JpaRepository<Dish, Integer> {
    
}