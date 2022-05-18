package com.example.fooddelivery.dto;


import com.example.fooddelivery.model.Customer;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class AddressDto {
    private String id;
    private String street;
    private String city;
    private String country;
    private Customer customer;
}
