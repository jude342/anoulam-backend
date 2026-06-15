package com.anoulam.anoulam_backend.dto;

public class DishDto {
    private Integer id;
    private String name;
    private String description;
    private String cooking_time;
    private String image_url;
    private String tutorial;

    //Constructor
    public DishDto() {

    }

    public DishDto(Integer id, String name, String description, String cooking_time, String image_url, String tutorial) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.cooking_time = cooking_time;
        this.image_url = image_url;
        this.tutorial = tutorial;
    }


    //Getter
    public Integer getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public String getCookingTime() {
        return this.cooking_time;
    }

    public String getImageUrl() {
        return this.image_url;
    }

    public String getTutorial() {
        return this.tutorial;
    }

    //Setter
    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCookingTime(String cooking_time) {
        this.cooking_time = cooking_time;
    }

    public void setImageUrl(String image_url) {
        this.image_url = image_url;
    }

    public void setTutorial(String tutorial) {
        this.tutorial = tutorial;
    }

    

}
