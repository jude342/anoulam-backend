package com.anoulam.anoulam_backend.model;
import jakarta.persistence.*;

@Entity
@Table(name = "dishes")
public class Dish {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "cooking_time")
    private String cooking_time;

    @Column(name = "image_url")
    private String image_url;

    @Column(name = "tutorial")
    private String tutorial;

    //Constructor
    public Dish() {

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
