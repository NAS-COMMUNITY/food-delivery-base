package com.example.fooddelivery.command;



import com.example.fooddelivery.enums.FoodType;
import com.example.fooddelivery.enums.Status;
import com.example.fooddelivery.util.AssertValidation;
import com.example.fooddelivery.util.Validate;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Set;


@Getter
@Setter
public class OrderEntityCommand implements Validate {
    private String customer;
    private String billingAddress;
    private String shippingAddress;
    private Status status;
    private String rejectReason;
    private Set<ProductCommand> productCommands;

    @Override
    public void validate() {
        AssertValidation.isEmpty(customer);
        AssertValidation.isEmpty(billingAddress);
        AssertValidation.isEmail(shippingAddress);
    }
}
