package com.example.fooddelivery.dto;


import com.example.fooddelivery.model.Address;
import com.example.fooddelivery.model.OrderEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class CustomerDto {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private Set<Address> addressDtos;
    private Set<OrderEntity> orderDtos;
}
