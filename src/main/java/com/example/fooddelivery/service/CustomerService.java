package com.example.fooddelivery.service;

import com.example.fooddelivery.command.AddressCommand;
import com.example.fooddelivery.command.CustomerCommand;
import com.example.fooddelivery.model.Address;
import com.example.fooddelivery.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomerService {

    Customer createCustomer(CustomerCommand customerCommand);
    Page<Customer> getAll(Pageable pageable);

    Address addAddressToCustomer(String customerId, AddressCommand addressCommand);

    Customer findById(String customerId);
}
