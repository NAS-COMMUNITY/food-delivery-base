package com.example.fooddelivery.dto;

import com.example.fooddelivery.model.Address;
import com.example.fooddelivery.model.Customer;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;


@Getter
@Setter
public class OrderDto {
    private String id;
    private Customer customer;
    private Address billingAddress;
    private Address shippingAddress;
    private String type;
    private String status;
    private BigDecimal price;
    private String rejectReason;
}
