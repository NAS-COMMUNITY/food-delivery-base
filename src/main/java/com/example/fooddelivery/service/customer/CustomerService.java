package com.example.fooddelivery.service.customer;

import com.example.fooddelivery.command.AddressCommand;
import com.example.fooddelivery.command.CustomerCommand;
import com.example.fooddelivery.command.OrderEntityCommand;
import com.example.fooddelivery.dto.CustomerDto;
import com.example.fooddelivery.model.Address;
import com.example.fooddelivery.model.Customer;
import com.example.fooddelivery.model.OrderEntity;
import com.example.fooddelivery.payload.JwtResponse;
import com.example.fooddelivery.payload.JwtSignUp;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomerService {

    Customer createCustomer(final CustomerCommand customerCommand);
    Page<CustomerDto> getAll(Pageable pageable);

    Address addAddressToCustomer(String customerId, AddressCommand addressCommand);

    Customer findById(String customerId);

    Customer update(String customerId, CustomerCommand customerCommand);

    Customer deleteCustomer(String customerId);

    Customer signup(JwtSignUp jwtSignUp);

    void changePasswordUser(Customer customer, String newPassword);

    Customer findByEmail(String email);
}
