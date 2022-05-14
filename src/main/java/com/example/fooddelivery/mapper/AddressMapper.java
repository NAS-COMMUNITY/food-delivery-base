package com.example.fooddelivery.mapper;


import com.example.fooddelivery.dto.AddressDto;
import com.example.fooddelivery.model.Address;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AddressMapper {

    AddressDto toAddressDto(Address address);

}
