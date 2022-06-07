package com.example.fooddelivery.command;

import com.example.fooddelivery.util.AssertValidation;
import com.example.fooddelivery.util.Validate;
import lombok.Getter;
import lombok.Setter;





@Getter
@Setter
public class ProductCommand implements Validate {
    private String type;

    @Override
    public void validate() {
        AssertValidation.isEmpty(type);
    }
}
