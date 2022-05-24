package com.example.fooddelivery.model;



import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.util.Assert;

import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Product extends AbstractEntity{

    private String name;
    private BigDecimal price;

    public Product(String name, BigDecimal price) {

        Assert.hasText(name, "Name must not be null or empty!");
        Assert.isTrue(BigDecimal.ZERO.compareTo(price) < 0, "Price must be greater than zero!");

        this.name = name;
        this.price = price;
    }
}
