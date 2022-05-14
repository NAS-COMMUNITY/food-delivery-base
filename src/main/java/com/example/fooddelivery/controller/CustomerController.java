package com.example.fooddelivery.controller;


import com.example.fooddelivery.command.AddressCommand;
import com.example.fooddelivery.command.CustomerCommand;
import com.example.fooddelivery.model.Address;
import com.example.fooddelivery.model.Customer;
import com.example.fooddelivery.service.CustomerService;
import com.example.fooddelivery.service.address.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import static com.example.fooddelivery.cons.ResourcePath.CUSTOMER;
import static com.example.fooddelivery.cons.ResourcePath.V1;

@RestController
@RequestMapping(V1 + CUSTOMER)
@RequiredArgsConstructor
public class CustomerController {


    private final CustomerService customerService;
    private final AddressService addressService;

    @GetMapping
    public Page<Customer> get(Pageable pageable){
        return customerService.getAll(pageable);
    }

    @PostMapping("/{id}/address/{addressId}")
    public Customer createOneWithAddress(@RequestBody CustomerCommand customerCommand, @PathVariable("addressId") String addressId){
        final Customer customer = customerService.createCustomer(addressId, customerCommand);
        //final Address address = addressService.createAddress(addressCommand);

        return customer;
    }
}
