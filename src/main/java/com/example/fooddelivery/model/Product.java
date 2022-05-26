package com.example.fooddelivery.model;


import com.example.fooddelivery.command.ProductCommand;
import com.example.fooddelivery.enums.FoodType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
public class Product extends AbstractEntity{

    @Enumerated(EnumType.STRING)
    private FoodType type;


    @ManyToOne(optional = false)
    private OrderEntity order;


    public Product(){

    }

    public Product(FoodType type) {
        this.type = type;
    }
    public static Product createOne(final ProductCommand productCommand){
        final Product product = new Product();
        product.type = FoodType.valueOf(productCommand.getType());

        return product;
    }
    public void update(final ProductCommand productCommand){
        this.type = FoodType.valueOf(productCommand.getType());
    }

    public void linkToOrder(OrderEntity order) {
        this.order = order;
    }

    @Override
    public void delete() {
        super.delete();
    }
}
