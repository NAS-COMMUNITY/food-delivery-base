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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

import static com.example.fooddelivery.cons.ResourcePath.CUSTOMER;
import static com.example.fooddelivery.cons.ResourcePath.V1;
import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

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

    @PostMapping()
    public Customer createOneWithAddress(@RequestBody CustomerCommand customerCommand){
        final Customer customer = customerService.createCustomer(customerCommand);

        return customer;
    }
    @PostMapping("/{customerId}/address")
    public ResponseEntity<Address> createAddress(@PathVariable final String customerId, @RequestBody final AddressCommand addressCommand){
        final Address address = customerService.addAddressToCustomer(customerId, addressCommand);
        final URI uri = fromCurrentRequest().path("/{id}").buildAndExpand(address.getId()).toUri();
        return ResponseEntity.created(uri).body(address);
    }
}
