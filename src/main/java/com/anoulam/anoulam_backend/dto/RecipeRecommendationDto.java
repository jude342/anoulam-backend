package com.anoulam.anoulam_backend.dto;

public class RecipeRecommendationDto {
    private Integer id;
    private String dishName;
    private String imageUrl;
    private String cookingTime;
    private double matchPercentage;

    //Constructor
    public RecipeRecommendationDto() {

    }

    public RecipeRecommendationDto(Integer id, String dishName, String imageUrl, String cookingTime, double matchPercentage) {
        this.id = id;
        this.dishName = dishName;
        this.imageUrl = imageUrl;
        this.cookingTime = cookingTime;
        this.matchPercentage = matchPercentage;
    }

    //Setter
    public void setId(Integer id) {
        this.id = id;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

     public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setCookingTime(String cookingTime) {
        this.cookingTime = cookingTime;
    }

    public void setMatchPercentage(double matchPercentage) {
        this.matchPercentage = matchPercentage;
    }


    //Getter
    public Integer getId() {
        return id;
    }

    public String getDishName() {
        return this.dishName;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public String getCookingTime() {
        return this.cookingTime;
    }

    public double getMatchPercentage() {
        return this.matchPercentage;
    }
}
