package com.anoulam.anoulam_backend.service;

import com.anoulam.anoulam_backend.dto.RecipeRecommendationDto;
import com.anoulam.anoulam_backend.model.Dish;
import com.anoulam.anoulam_backend.model.Dish_Ingredient;
import com.anoulam.anoulam_backend.model.Ingredient;
import com.anoulam.anoulam_backend.repository.DishRepository;
import com.anoulam.anoulam_backend.repository.Dish_IngredientRepository;
import com.anoulam.anoulam_backend.repository.IngredientRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class RecommendationService {

        private final IngredientRepository ingredientRepository;
        private final Dish_IngredientRepository dishIngredientRepository;
        private final DishRepository dishRepository;

        public RecommendationService(
                        IngredientRepository ingredientRepository,
                        Dish_IngredientRepository dishIngredientRepository,
                        DishRepository dishRepository) {
                this.ingredientRepository = ingredientRepository;
                this.dishIngredientRepository = dishIngredientRepository;
                this.dishRepository = dishRepository;
        }

        public List<RecipeRecommendationDto> recommend(List<String> ingredients) {

                // Normalize user inputs once
                List<String> normalizedInputs = ingredients.stream()
                                .map(String::trim)
                                .map(String::toLowerCase)
                                .distinct()
                                .toList();

                // Load ingredients from database
                List<Ingredient> dbIngredients = ingredientRepository.findAll();

                // Match ingredients
                List<Ingredient> matchedIngredients = dbIngredients.stream()
                                .filter(dbIngredient -> {

                                        String dbName = dbIngredient.getName()
                                                        .trim()
                                                        .toLowerCase();

                                        String filipinoName;
                                        String englishName;

                                        if (dbName.contains("(")) {

                                                filipinoName = dbName.split("\\(")[0].trim();

                                                englishName = dbName.substring(
                                                                dbName.indexOf("(") + 1,
                                                                dbName.indexOf(")")).trim();

                                        } else {

                                                filipinoName = dbName;
                                                englishName = "";
                                        }

                                        return normalizedInputs.stream()
                                                        .anyMatch(userInput ->

                                                        filipinoName.equalsIgnoreCase(userInput)

                                                                        ||

                                                                        englishName.equalsIgnoreCase(userInput)

                                                                        ||

                                                                        (userInput.length() >= 3
                                                                                        &&
                                                                                        (filipinoName.contains(
                                                                                                        userInput)
                                                                                                        ||
                                                                                                        englishName.contains(
                                                                                                                        userInput))));

                                })
                                .toList();

                // Extract matched ingredient IDs
                List<Integer> matchedIngredientIds = matchedIngredients.stream()
                                .map(Ingredient::getId)
                                .toList();

                if (matchedIngredientIds.isEmpty()) {
                        return List.of();
                }

                // Find candidate dish relations
                List<Dish_Ingredient> matchedDishIngredients = dishIngredientRepository.findByIngredientIdIn(
                                matchedIngredientIds);

                List<Integer> dishIds = matchedDishIngredients.stream()
                                .map(Dish_Ingredient::getDishId)
                                .distinct()
                                .toList();

                // Get all ingredient requirements for those dishes
                List<Dish_Ingredient> totalIngredientNeeds = dishIngredientRepository.findByDishIdIn(
                                dishIds);

                // Total ingredients per dish
                Map<Integer, Long> totalIngredientPerDish = totalIngredientNeeds.stream()
                                .collect(Collectors.groupingBy(
                                                Dish_Ingredient::getDishId,
                                                Collectors.counting()));

                // Matched ingredients per dish
                Map<Integer, Long> matchedIngredientsPerDish = matchedDishIngredients.stream()
                                .collect(Collectors.groupingBy(
                                                Dish_Ingredient::getDishId,
                                                Collectors.counting()));

                // Calculate percentages
                Map<Integer, Double> dishMatchPercentages = new HashMap<>();

                for (Integer dishId : dishIds) {

                        long matchedCount = matchedIngredientsPerDish.getOrDefault(
                                        dishId,
                                        0L);

                        long totalCount = totalIngredientPerDish.getOrDefault(
                                        dishId,
                                        1L);

                        double percentage = ((double) matchedCount / totalCount) * 100;

                        percentage = Math.round(percentage * 10.0) / 10.0;

                        dishMatchPercentages.put(
                                        dishId,
                                        percentage);
                }

                // Fetch only candidate dishes
                List<Dish> dishes = dishRepository.findAllById(
                                dishIds);

                // Build DTOs and sort
                return dishes.stream()
                                .map(dish -> new RecipeRecommendationDto(
                                                dish.getId(),
                                                dish.getName(),
                                                dish.getImageUrl(),
                                                dish.getCookingTime(),
                                                dishMatchPercentages.getOrDefault(
                                                                dish.getId(),
                                                                0.0)))
                                .sorted((a, b) -> Double.compare(
                                                b.getMatchPercentage(),
                                                a.getMatchPercentage()))
                                .collect(Collectors.toList());
        }
}