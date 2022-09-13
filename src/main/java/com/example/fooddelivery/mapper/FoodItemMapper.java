package com.example.fooddelivery.mapper;


import com.example.fooddelivery.dto.FoodItemDto;
import com.example.fooddelivery.model.FoodItem;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {OrderMapper.class})
public interface FoodItemMapper {

    FoodItemDto toProductDto(FoodItem foodItem);
}
