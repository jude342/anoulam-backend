package com.anoulam.anoulam_backend.model;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;

@Entity
@Table(name = "dish_ingredients")
@IdClass(DishIngredientId.class)
public class Dish_Ingredient {

    @Id
    @Column(name = "dish_id")
    private Integer dishId;

    @Id
    @Column(name = "ingredient_id")
    private Integer ingredientId;

    public Dish_Ingredient() {
    }

    public Dish_Ingredient(Integer dishId, Integer ingredientId) {
        this.dishId = dishId;
        this.ingredientId = ingredientId;
    }

    public Integer getDishId() {
        return dishId;
    }

    public Integer getIngredientId() {
        return ingredientId;
    }

    public void setDishId(Integer dishId) {
        this.dishId = dishId;
    }

    public void setIngredientId(Integer ingredientId) {
        this.ingredientId = ingredientId;
    }
}

