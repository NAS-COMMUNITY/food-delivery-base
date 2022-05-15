package com.example.fooddelivery.mapper;

import com.example.fooddelivery.dto.OrderDto;
import com.example.fooddelivery.model.OrderEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderDto toOrderDto(OrderEntity orderEntity);
}
