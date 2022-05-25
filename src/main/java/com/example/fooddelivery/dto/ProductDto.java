package com.example.fooddelivery.dto;


import com.example.fooddelivery.enums.FoodType;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductDto {
    private String type;
    private BigDecimal price;
}
