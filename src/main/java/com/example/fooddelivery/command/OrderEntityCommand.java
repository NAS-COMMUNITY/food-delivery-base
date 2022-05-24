package com.example.fooddelivery.command;



import com.example.fooddelivery.enums.FoodType;
import com.example.fooddelivery.enums.Status;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Set;


@Getter
@Setter
public class OrderEntityCommand {
    private String customer;
    private String billingAddress;
    private String shippingAddress;
    private FoodType type;
    private Status status;
    private BigDecimal price;
    private String rejectReason;
}
