package com.example.fooddelivery.dto;

import com.example.fooddelivery.enums.FoodType;
import com.example.fooddelivery.model.Address;
import com.example.fooddelivery.model.Customer;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class OrderDto {
    private Customer customer;
    private Address billingAddress;
    private Address shippingAddress;
    private FoodType type;
}
