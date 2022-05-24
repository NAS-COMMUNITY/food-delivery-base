package com.example.fooddelivery.model;



import com.example.fooddelivery.command.AddressCommand;
import com.example.fooddelivery.command.CustomerCommand;
import com.example.fooddelivery.command.OrderEntityCommand;
import com.example.fooddelivery.enums.FoodType;
import com.example.fooddelivery.enums.Status;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.criterion.Order;
import org.springframework.util.Assert;

import javax.persistence.*;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static com.example.fooddelivery.enums.Status.PENDING;

@Entity
@Getter
@Setter
public class OrderEntity extends AbstractEntity{

    @ManyToOne(optional = false)
    private Customer customer;

    @ManyToOne(optional = false)
    private Address billingAddress;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    private Address shippingAddress;

    @Enumerated(EnumType.STRING)
    private Status status = PENDING;

    @OneToMany
    private Set<Pane> pane = new HashSet<Pane>();

    private BigDecimal price = BigDecimal.ZERO;

    private String rejectReason;

    public OrderEntity(){

    }
    public OrderEntity(Customer customer, Address shippingAddress){
        this(customer, shippingAddress, null);
    }

    public static OrderEntity createOne(final OrderEntityCommand orderEntityCommand,
                                        final Customer customer,
                                        final Address billingAddress,
                                        final Address shippingAddress){
        final OrderEntity orderEntity = new OrderEntity();

        orderEntity.customer = customer;
        orderEntity.billingAddress = billingAddress;
        orderEntity.shippingAddress = shippingAddress;
        orderEntity.price = orderEntityCommand.getPrice();

        return orderEntity;
    }

    public void addToPane(Pane pane){
        this.pane.add(pane);
    }

    public OrderEntity(Customer customer, Address billingAddress, Address shippingAddress) {

        this.customer = customer;
        this.shippingAddress = shippingAddress;
        this.billingAddress = billingAddress;
    }
    public void valide(){
        this.status = Status.VALIDATED;
    }
    public void reject(String why){
        this.status = Status.REJECTED;
        this.rejectReason = why;
    }
    public Address linkToAddress(final AddressCommand billingAddress){
        final Address address = Address.create(billingAddress);

        return address;
    }
    public Set<Pane> getPane() {
        return Collections.unmodifiableSet(pane);
    }
    public BigDecimal getTotal() {

        BigDecimal total = BigDecimal.ZERO;

        for (Pane item : pane) {
            total = total.add(item.getTotal());
        }

        return total;
    }
    @Override
    public void delete() {
        super.delete();
    }

    public Address getBillingAddress() {
        return billingAddress != null ? billingAddress : shippingAddress;
    }
    public Address getShippingAddress() {
        return shippingAddress;
    }
}
