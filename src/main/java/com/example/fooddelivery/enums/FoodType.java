package com.example.fooddelivery.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FoodType {
    PIZZA,
    TACOS;

    private  Double pizza = 300.00;
    private  Double tacos = 400.00;

    FoodType() {

    }
    FoodType(Double pizza, Double tacos, Double pizza1, Double tacos1){

        this.pizza = pizza1;
        this.tacos = tacos1;
    }


}
