package com.example.fooddelivery.service;

import com.example.fooddelivery.command.CustomerCommand;
import com.example.fooddelivery.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomerService {

    Customer createCustomer(String addressId, CustomerCommand customerCommand);
    Page<Customer> getAll(Pageable pageable);
}
