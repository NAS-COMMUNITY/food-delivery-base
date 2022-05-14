package com.example.fooddelivery.model;


import com.example.fooddelivery.enums.FoodType;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
public class Product extends AbstractEntity{

    @Enumerated(EnumType.STRING)
    private FoodType foodType;
    private Double price;
    public Product(){

    }
}
