package com.example.fooddelivery.model;


import com.example.fooddelivery.command.AddressCommand;
import com.example.fooddelivery.command.CustomerCommand;
import com.example.fooddelivery.command.OrderEntityCommand;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.criterion.Order;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
public class Customer extends AbstractEntity{

    private String firstName;
    private String lastName;

    //  a single email address cannot be used by multiple customers
    private String email;

    /**
     * whenever we initially persist, update, or delete a customer,
     * the Addresses will be persisted, updated, or deleted as well.
     *
     * orphanRemoval => delete orphaned entities from the database,
     * An entity that is no longer attached to its parent is the definition of being an orphan.
     *
     * @JoinColumn => add another column to the table backing the Address object.
     * This additional column will then be used to refer to the Customer to allow joining the tables
     */
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "customer")
    @JsonIgnore
    private Set<Address> addresses;

    public Customer(){

    }

    public Customer(String firstName, String lastName) {

        this.firstName = firstName;
        this.lastName = lastName;
    }
    public static Customer createOne(final CustomerCommand customerCommand){
        final Customer customer = new Customer();

        customer.firstName = customerCommand.getFirstName();
        customer.lastName = customerCommand.getLastName();
        customer.email = customerCommand.getEmail();
        customer.addresses = createAddress(customerCommand.getAddressCommands());
        customer.addresses.forEach(address -> address.linkToCustomer(customer));

        return customer;
    }

    public Address addAddress(final AddressCommand addressCommand){
        final Address address = Address.create(addressCommand);

        address.linkToCustomer(this);

        return address;
    }
    public static Set<Address> createAddress(final Set<AddressCommand> addressCommands){
        return addressCommands.stream().map(Address::create).collect(Collectors.toSet());
    }
    public void update(final CustomerCommand customerCommand){
        this.firstName = customerCommand.getFirstName();
        this.lastName = customerCommand.getLastName();
        this.email = customerCommand.getEmail();
    }
    @Override
    public void delete() {
        super.delete();
    }
}