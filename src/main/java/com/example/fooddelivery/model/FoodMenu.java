package com.example.fooddelivery.model;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "FOOD_MENU")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class FoodMenu extends AbstractEntity{

    private String menuName;

    @OneToMany
    private Set<Category> categories;

    public static FoodMenu create(final String menuName, Set<Category> categories){
        final FoodMenu foodMenu = new FoodMenu();

        foodMenu.menuName = menuName;
        foodMenu.categories = categories;

        return foodMenu;
    }
}
