package com.example.fooddelivery.model;



import com.example.fooddelivery.enums.Status;
import org.springframework.util.Assert;

import javax.persistence.*;

@Entity
public class OrderEntity extends AbstractEntity{

    @ManyToOne(optional = false)
    private Customer customer;

    @ManyToOne
    private Address billingAddress;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    private Address shippingAddress;

    @Enumerated(EnumType.STRING)
    private Status status;

    public OrderEntity(){

    }
    public OrderEntity(Customer customer, Address shippingAddress){
        this(customer, shippingAddress, null);
    }

    public OrderEntity(Customer customer, Address billingAddress, Address shippingAddress) {
        Assert.notNull(customer);
        Assert.notNull(shippingAddress);

        this.customer = customer;
        this.shippingAddress = shippingAddress;
        this.billingAddress = billingAddress;
    }
}
