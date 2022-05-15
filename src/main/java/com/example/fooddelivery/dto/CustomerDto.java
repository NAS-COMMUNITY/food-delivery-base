package com.example.fooddelivery.dto;


import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class CustomerDto {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private Set<AddressDto> addressDtos;
    private Set<OrderDto> orderDtos;
}
