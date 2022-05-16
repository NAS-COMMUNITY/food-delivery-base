package com.example.fooddelivery.dto;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class OrderDto {
    private CustomerDto customer;
    private AddressDto billingAddress;
    private AddressDto shippingAddress;
    private String type;
}
