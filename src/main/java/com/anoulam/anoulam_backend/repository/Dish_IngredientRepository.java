package com.anoulam.anoulam_backend.repository;

import com.anoulam.anoulam_backend.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public interface Dish_IngredientRepository extends JpaRepository<Dish_Ingredient, DishIngredientId> {

    //Find Match Ingredients and Dish by Ingredient Id
    List<Dish_Ingredient> findByIngredientIdIn(List<Integer> ingredientIds);

    //Find Dishes Ids
    List<Dish_Ingredient> findByDishIdIn(List<Integer> dishIds);
}
