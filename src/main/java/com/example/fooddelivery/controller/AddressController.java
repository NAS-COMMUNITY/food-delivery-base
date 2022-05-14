package com.example.fooddelivery.controller;


import com.example.fooddelivery.command.AddressCommand;
import com.example.fooddelivery.model.Address;
import com.example.fooddelivery.service.address.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import static com.example.fooddelivery.cons.ResourcePath.ADDRESS;
import static com.example.fooddelivery.cons.ResourcePath.V1;

@RestController
@RequiredArgsConstructor
@RequestMapping(V1 + ADDRESS)
public class AddressController {

    private  final AddressService addressService;


    @GetMapping
    public Page<Address> getAll(Pageable pageable){
        return addressService.getAll(pageable);
    }
}
