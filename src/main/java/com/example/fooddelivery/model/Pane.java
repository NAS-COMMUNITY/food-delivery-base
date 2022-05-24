package com.example.fooddelivery.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.util.Assert;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Getter
@Setter
public class Pane{

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private String id;

    @ManyToOne
    private Product product;

    @Column(nullable = false)
    private BigDecimal price;
    private int amount;

    public Pane() {

    }

    public Pane(Product product, int amount) {

        Assert.notNull(product, "The given Product must not be null!");
        Assert.isTrue(amount > 0, "The amount of Products to be bought must be greater than 0!");

        this.product = product;
        this.amount = amount;
        this.price = product.getPrice();
    }
    public BigDecimal getTotal() {
        return price.multiply(BigDecimal.valueOf(amount));
    }
}
