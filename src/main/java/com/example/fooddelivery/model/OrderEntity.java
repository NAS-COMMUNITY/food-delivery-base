package com.example.fooddelivery.model;



import com.example.fooddelivery.command.AddressCommand;
import com.example.fooddelivery.command.OrderEntityCommand;
import com.example.fooddelivery.command.ProductCommand;
import com.example.fooddelivery.enums.FoodType;
import com.example.fooddelivery.enums.Status;
import com.example.fooddelivery.service.product.ProductService;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static com.example.fooddelivery.enums.Status.PENDING;
import static com.example.fooddelivery.enums.Status.REJECTED;

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
    /**
     * LAZY = fetch when needed
     * EAGER = fetch immediately
     * If your need more check ====> https://stackoverflow.com/questions/2990799/difference-between-fetchtype-lazy-and-eager-in-java-persistence-api
     */
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "order")
    @JsonIgnore
    private Set<Product> products;

    private Double price;

    private String rejectReason;

    public OrderEntity(){

    }

    public static OrderEntity createOne(final OrderEntityCommand orderEntityCommand,
                                        final Customer customer,
                                        final Address billingAddress,
                                        final Address shippingAddress){
        final OrderEntity orderEntity = new OrderEntity();

        orderEntity.customer = customer;
        orderEntity.billingAddress = billingAddress;
        orderEntity.shippingAddress = shippingAddress;
        orderEntity.products = createProduct(orderEntityCommand.getProductCommands());
        orderEntity.products.forEach(product -> product.linkToOrder(orderEntity));

        return orderEntity;
    }
    public OrderEntity(Customer customer, Address billingAddress, Address shippingAddress) {

        this.customer = customer;
        this.shippingAddress = shippingAddress;
        this.billingAddress = billingAddress;
    }
    public static Set<Product> createProduct(Set<ProductCommand> productCommands){
        return productCommands.stream().map(Product::createOne).collect(Collectors.toSet());
    }
    public Product addProduct(final ProductCommand productCommand){
        final Product product = Product.createOne(productCommand);

        product.linkToOrder(this);

        return product;
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
    public void linkToProduct(Product product){
        this.products.add(product);
    }
    public void update(final  OrderEntityCommand orderEntityCommand){

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
