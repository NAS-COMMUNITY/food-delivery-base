package com.example.fooddelivery.command;



import com.example.fooddelivery.enums.FoodType;
import com.example.fooddelivery.enums.Status;
import com.example.fooddelivery.model.Address;
import com.example.fooddelivery.model.Customer;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;



@Getter
@Setter
public class OrderEntityCommand {
    private Customer customer;
    private Address billingAddress;
    private Address shippingAddress;
    private FoodType type;
    private Status status;
    private BigDecimal price;
    private String rejectReason;
}
