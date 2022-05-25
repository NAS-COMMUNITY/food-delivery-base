package com.example.fooddelivery.dto;

import com.example.fooddelivery.enums.FoodType;
import com.example.fooddelivery.enums.Status;
import com.example.fooddelivery.model.Product;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;


@Getter
@Setter
public class OrderDto {
    private String id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private CustomerDto customer;
    private AddressDto billingAddress;
    private AddressDto shippingAddress;
    private Set<ProductDto> productDtoSet;
    private Status status;
    private Double price;
    private String rejectReason;

}
