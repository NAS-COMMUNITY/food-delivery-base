package com.example.fooddelivery.command;

import com.example.fooddelivery.enums.FoodType;
import com.example.fooddelivery.util.AssertValidation;
import com.example.fooddelivery.util.Validate;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;




@Getter
@Setter
public class ProductCommand implements Validate {
    private String type;

    @Override
    public void validate() {
        AssertValidation.isEmpty(type);
    }
}
