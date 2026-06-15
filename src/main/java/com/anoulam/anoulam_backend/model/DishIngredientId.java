package com.anoulam.anoulam_backend.model;

import java.io.Serializable;
import java.util.Objects;

public class DishIngredientId implements Serializable {

    private Integer dishId;
    private Integer ingredientId;

    public DishIngredientId() {
    }

    public DishIngredientId(Integer dishId, Integer ingredientId) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DishIngredientId that = (DishIngredientId) o;
        return Objects.equals(dishId, that.dishId) && Objects.equals(ingredientId, that.ingredientId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dishId, ingredientId);
    }
}
