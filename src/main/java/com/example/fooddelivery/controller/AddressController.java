package com.example.fooddelivery.controller;


import com.example.fooddelivery.command.AddressCommand;
import com.example.fooddelivery.model.Address;
import com.example.fooddelivery.service.address.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;

import static com.example.fooddelivery.cons.ResourcePath.ADDRESS;
import static com.example.fooddelivery.cons.ResourcePath.V1;

@RestController
@RequiredArgsConstructor
@RequestMapping(V1 + ADDRESS)
public class AddressController {

    private  final AddressService addressService;


    @PostMapping
    public Address createAddress(@RequestBody AddressCommand addressCommand){
        final Address address = addressService.createAddress(addressCommand);

        return address;
    }
    @GetMapping
    public Address get()
}
