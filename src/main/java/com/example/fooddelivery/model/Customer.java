package com.example.fooddelivery.model;


import com.example.fooddelivery.command.AddressCommand;
import com.example.fooddelivery.command.CustomerCommand;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.util.Assert;

import javax.persistence.*;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
public class Customer extends AbstractEntity{

    private String firstName;
    private String lastName;

    @Column(unique = true) //  a single email address cannot be used by multiple customers
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
    @JsonIgnore
    private Set<Address> addresses;
    public Customer(){

    }

    public Customer(String firstName, String lastName) {

        Assert.hasText(firstName);
        Assert.hasText(lastName);

        this.firstName = firstName;
        this.lastName = lastName;
    }
    public static Customer createOne(final CustomerCommand customerCommand, final Set<AddressCommand> addressCommands){
        final Customer customer = new Customer();

        customer.firstName = customerCommand.getFirstName();
        customer.lastName = customerCommand.getLastName();
        customer.email = customerCommand.getEmail();
        customer.addresses = createAddress(addressCommands);
        customer.addresses.forEach(address -> address.setCustomer(customer));

        return customer;
    }

    public Address addAddress(final AddressCommand addressCommand){
        final Address address = Address.create(addressCommand);

        return address;
    }
    public static Set<Address> createAddress(final Set<AddressCommand> addressCommands){
        return addressCommands.stream().map(Address::create).collect(Collectors.toSet());
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(Set<Address> addresses) {
        this.addresses = addresses;
    }
}
