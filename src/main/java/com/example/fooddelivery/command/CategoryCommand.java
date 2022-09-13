package com.example.fooddelivery.command;


import com.example.fooddelivery.model.FoodItem;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import java.util.Set;

@Getter
@Setter
@RequiredArgsConstructor
public class CategoryCommand {
    private final String name;
    private final Set<FoodItemCommand> foodItems;
}
