package com.example.fooddelivery.dto;

import com.example.fooddelivery.enums.FoodType;
import com.example.fooddelivery.enums.Status;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDateTime;


@Getter
@Setter
public class OrderDto {
    private String id;
    private CustomerDto customer;
    private AddressDto billingAddress;
    private AddressDto shippingAddress;
    private String type;
    private Status status;
    private BigDecimal price;
    private String rejectReason;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
