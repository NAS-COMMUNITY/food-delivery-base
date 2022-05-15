package com.example.fooddelivery.controller;


import com.example.fooddelivery.command.AddressCommand;
import com.example.fooddelivery.command.CustomerCommand;
import com.example.fooddelivery.command.OrderEntityCommand;
import com.example.fooddelivery.dto.AddressDto;
import com.example.fooddelivery.dto.CustomerDto;
import com.example.fooddelivery.mapper.AddressMapper;
import com.example.fooddelivery.mapper.CustomerMapper;
import com.example.fooddelivery.model.Address;
import com.example.fooddelivery.model.Customer;
import com.example.fooddelivery.model.OrderEntity;
import com.example.fooddelivery.service.customer.CustomerService;
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
    private final CustomerMapper customerMapper;
    private final AddressMapper addressMapper;

    @GetMapping("/all")
    public ResponseEntity<Page<CustomerDto>> getAllCustomer(Pageable pageable){
        return ResponseEntity.ok(customerService.getAll(pageable));
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerDto> getCustomerById(@PathVariable("customerId") String customerId){
        final Customer customer = customerService.findById(customerId);

        return ResponseEntity.ok(customerMapper.toCustomerDto(customer));
    }
    @PostMapping()
    public ResponseEntity<CustomerDto> createOne(@RequestBody CustomerCommand customerCommand)throws NullPointerException{
        final Customer customer = customerService.createCustomer(customerCommand);
        final URI uri = fromCurrentRequest().path("/{id}").buildAndExpand(customer.getId()).toUri();
        return ResponseEntity.created(uri).body(customerMapper.toCustomerDto(customer));
    }
    @PostMapping("/{customerId}/address")
    public ResponseEntity<AddressDto> createAddress(@PathVariable final String customerId, @RequestBody final AddressCommand addressCommand){
        final Address address = customerService.addAddressToCustomer(customerId, addressCommand);
        final URI uri = fromCurrentRequest().path("/{id}").buildAndExpand(address.getId()).toUri();
        return ResponseEntity.created(uri).body(addressMapper.toAddressDto(address));
    }
    @PutMapping("/{customerId}")
    public ResponseEntity<CustomerDto> updateCustomer(@PathVariable("customerId") String customerId, @RequestBody final CustomerCommand customerCommand){
        final Customer customer = customerService.update(customerId, customerCommand);

        return ResponseEntity.ok().body(customerMapper.toCustomerDto(customer));
    }
    @DeleteMapping("/{customerId}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable("customerId") String customerId){
        final  Customer customer = customerService.deleteCustomer(customerId);

        return ResponseEntity.noContent().build();
    }
}
