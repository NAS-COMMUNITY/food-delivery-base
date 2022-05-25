package com.example.fooddelivery.mapper;


import com.example.fooddelivery.dto.ProductDto;
import com.example.fooddelivery.model.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {OrderMapper.class})
public interface ProductMapper {

    ProductDto toProductDto(Product product);
}
