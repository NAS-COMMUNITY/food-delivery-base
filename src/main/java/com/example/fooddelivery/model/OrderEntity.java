package com.example.fooddelivery.model;



import com.example.fooddelivery.command.AddressCommand;
import com.example.fooddelivery.command.FoodItemCommand;
import com.example.fooddelivery.command.OrderEntityCommand;
import com.example.fooddelivery.enums.Status;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

import java.util.Set;
import java.util.stream.Collectors;

import static com.example.fooddelivery.enums.Status.PENDING;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderEntity extends AbstractEntity{
    @ManyToOne(optional = false)
    private Address billingAddress;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    private Address shippingAddress;

    @Enumerated(EnumType.STRING)
    private Status status;
    /**
     * LAZY = fetch when needed
     * EAGER = fetch immediately
     * If your need more check ====> https://stackoverflow.com/questions/2990799/difference-between-fetchtype-lazy-and-eager-in-java-persistence-api
     */
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "order")
    @JsonIgnore
    private Set<FoodItem> foodItems;

    private Double price;

    private String rejectReason;

    @OneToOne
    private Payment payment;

    public static OrderEntity createOne(final OrderEntityCommand orderEntityCommand,
                                        final Address billingAddress,
                                        final Address shippingAddress){
        final OrderEntity orderEntity = new OrderEntity();


        orderEntity.billingAddress = billingAddress;
        orderEntity.shippingAddress = shippingAddress;
        orderEntity.foodItems = createProduct(orderEntityCommand.getFoodItemCommands());


        return orderEntity;
    }
    public static Set<FoodItem> createProduct(Set<FoodItemCommand> foodItemCommands){
        return foodItemCommands.stream().map(FoodItem::createOne).collect(Collectors.toSet());
    }
    public FoodItem addFoodItem(final FoodItemCommand foodItemCommand){
        final FoodItem foodItem = FoodItem.createOne(foodItemCommand);
        foodItem.linkToOrder(this);
        this.price +=  foodItem.getPrice();
        return foodItem;
    }
    public void removeFoodItem(FoodItem foodItem){
        this.foodItems.remove(foodItem);
        this.price -= foodItem.getPrice();
    }
    public Address linkToAddress(final AddressCommand billingAddress){
        final Address address = Address.create(billingAddress);

        return address;
    }
    public void update(final  OrderEntityCommand orderEntityCommand){

    }
    @Override
    public void delete() {
        super.delete();
    }

}
