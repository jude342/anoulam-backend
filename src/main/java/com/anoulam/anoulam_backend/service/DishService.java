package com.anoulam.anoulam_backend.service;

import com.anoulam.anoulam_backend.repository.*;

import org.springframework.stereotype.Service;

import com.anoulam.anoulam_backend.dto.DishDto;

@Service
public class DishService {
    private final DishRepository dishRepository;

    public DishService(DishRepository dishRepository) {
        this.dishRepository = dishRepository;
    }

    public DishDto getDishById(Integer dishId) {
        if (dishId == null) {
            return null;
        }

        return dishRepository.findById(dishId)
                .map(dish -> new DishDto(
                        dish.getId(),
                        dish.getName(),
                        dish.getDescription(),
                        dish.getCookingTime(),
                        dish.getImageUrl(),
                        dish.getTutorial()))
                .orElse(null);
    }



    // =========== FOR FUTURE FEATURE (TOP DISHES, RATING FEATURE) ===========
    // public List<RecipeRecommendationDto> getTopDishes(List<Integer> dishIds) {
    //     if (dishIds.isEmpty()) {
    //         return List.of();
    //     }

    //     return dishRepository.findAllById(dishIds)
    //             .stream()
    //             .map(dish -> new RecipeRecommendationDto(dish.getId(), dish.getName(), dish.getImageUrl(),
    //                     dish.getCookingTime(), 100))
    //             .toList();
    // }

}
