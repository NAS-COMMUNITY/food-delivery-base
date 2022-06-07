package com.example.fooddelivery.dto;


import com.example.fooddelivery.enums.FoodType;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class ProductDto {
    private String id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private FoodType type;
    private BigDecimal price;
}