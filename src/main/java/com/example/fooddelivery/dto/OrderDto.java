package com.example.fooddelivery.dto;

import com.example.fooddelivery.enums.FoodType;
import com.example.fooddelivery.enums.Status;
import com.example.fooddelivery.model.Address;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;


@Getter
@Setter
public class OrderDto {
    private String id;
    private CustomerDto customer;
    private AddressDto billingAddress;
    private AddressDto shippingAddress;
    private FoodType type;
    private Status status;
    private BigDecimal price;
    private String rejectReason;
}
