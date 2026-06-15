package com.anoulam.anoulam_backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "ingredients")
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    //Constructor
    public Ingredient() {
    }

    public Ingredient(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    //Getter
    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    //Setter
    public void setName(String name) {
        this.name = name;
    }
}
