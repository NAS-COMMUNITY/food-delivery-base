package com.example.fooddelivery;

import com.example.fooddelivery.model.Address;
import com.example.fooddelivery.model.Customer;
import com.example.fooddelivery.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
@EnableJpaAuditing
public class FoodDeliveryApplication implements CommandLineRunner {

    @Autowired
    private CustomerRepository customerRepository;

    public static void main(String[] args) {
        SpringApplication.run(FoodDeliveryApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Address address = new Address("azfaf", "fdqfqqs","sdfsv");
        Customer customer = new Customer("anas", "abbal");
        address.setCustomer(customer);


        Set<Address> addresses = new HashSet<>();

        customer.setAddresses(addresses);
        addresses.add(address);



        customerRepository.save(customer);
    }
}
