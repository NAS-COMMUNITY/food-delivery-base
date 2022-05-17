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
    private String customer;
    private String billingAddress;
    private String shippingAddress;
    private String type;
    private String status;
    private BigDecimal price;
    private String rejectReason;
}
