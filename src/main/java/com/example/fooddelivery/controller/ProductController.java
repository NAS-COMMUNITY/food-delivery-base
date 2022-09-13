package com.example.fooddelivery.controller;



import com.example.fooddelivery.command.FoodItemCommand;
import com.example.fooddelivery.dto.FoodItemDto;
import com.example.fooddelivery.mapper.FoodItemMapper;
import com.example.fooddelivery.model.FoodItem;
import com.example.fooddelivery.service.foodItem.FoodItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.example.fooddelivery.cons.ResourcePath.*;

@RestController
@RequestMapping(PRODUCT)
@RequiredArgsConstructor
public class ProductController {

    private final FoodItemService foodItemService;
    private final FoodItemMapper foodItemMapper;

    @GetMapping
    public ResponseEntity<Page<FoodItemDto>> getFoodItems(Pageable pageable){

        return ResponseEntity.ok(foodItemService.getAllFoodItems(pageable));
    }
    @GetMapping("/{productId}")
    public ResponseEntity<FoodItemDto> getOne(@PathVariable("productId") final String productId){
        final FoodItem foodItem = foodItemService.findOneFoodItem(productId);

        return ResponseEntity.ok(foodItemMapper.toProductDto(foodItem));
    }
    @PutMapping("/{productId}")
    public ResponseEntity<FoodItemDto> update(@PathVariable("productId")final String productId,
                                                     final FoodItemCommand foodItemCommand){
        final FoodItem foodItem = foodItemService.updateFoodItem(productId, foodItemCommand);
        return ResponseEntity.ok(foodItemMapper.toProductDto(foodItem));
    }
    @DeleteMapping("/{productId}")
    public ResponseEntity<FoodItemDto> deleteById(@PathVariable("productId")final String productId){
        foodItemService.deleteById(productId);
        return ResponseEntity.noContent().build();
    }
}
