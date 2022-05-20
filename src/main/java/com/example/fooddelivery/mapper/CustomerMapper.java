package com.example.fooddelivery.mapper;


import com.example.fooddelivery.dto.CustomerDto;
import com.example.fooddelivery.model.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    CustomerDto toCustomerDto(Customer customer);
}
