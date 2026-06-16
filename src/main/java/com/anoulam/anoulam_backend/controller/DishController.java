package com.anoulam.anoulam_backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anoulam.anoulam_backend.dto.DishDto;
import com.anoulam.anoulam_backend.service.DishService;

@RestController
@RequestMapping("/api/v1/dishes")
public class DishController {
    private final DishService dishService;

    public DishController(DishService dishService) {
        this.dishService = dishService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<DishDto> getDishById(@PathVariable Integer id) {
        try {

            DishDto dish = dishService.getDishById(id);
            if (dish == null) {
                return ResponseEntity.notFound().build();
            }

            return ResponseEntity.ok(dish);

        } catch (Exception e) {

            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
                    
        }
    }

    // =========== FOR FUTURE FEATURE (TOP DISHES, RATING FEATURE) ===========
    // @GetMapping("/list")
    // public ResponseEntity<List<RecipeRecommendationDto>> getTopDishById(
    // @RequestParam String dishIds) {

    // try {

    // List<Integer> ids = Arrays.stream(dishIds.split(","))
    // .map(String::trim)
    // .filter(id -> !id.isEmpty())
    // .map(Integer::parseInt)
    // .toList();

    // List<RecipeRecommendationDto> topDishes = dishService.getTopDishes(ids);

    // return ResponseEntity.ok(topDishes);

    // } catch (Exception e) {

    // System.out.println("CRASH LOG:");
    // e.printStackTrace();

    // return ResponseEntity.status(500).build();
    // }
    // }
}
