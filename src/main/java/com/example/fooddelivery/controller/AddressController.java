package com.example.fooddelivery.controller;


import com.example.fooddelivery.command.AddressCommand;
import com.example.fooddelivery.dto.AddressDto;
import com.example.fooddelivery.mapper.AddressMapper;
import com.example.fooddelivery.model.Address;
import com.example.fooddelivery.service.address.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.example.fooddelivery.cons.ResourcePath.ADDRESS;
import static com.example.fooddelivery.cons.ResourcePath.V1;

@RestController
@RequiredArgsConstructor
@RequestMapping(V1 + ADDRESS)
public class AddressController {

    private  final AddressService addressService;
    private final AddressMapper addressMapper;

    @GetMapping
    public ResponseEntity<Page<AddressDto>> getAll(Pageable pageable){
        return ResponseEntity.ok(addressService.getAll(pageable));
    }
    @PutMapping("/{addressId}")
    public ResponseEntity<AddressDto> updateAddress(@PathVariable("addressId") String addressId,
                                                    @RequestBody AddressCommand addressCommand){
        final Address address = addressService.update(addressId, addressCommand);

        return ResponseEntity.ok(addressMapper.toAddressDto(address));
    }
    @DeleteMapping("/{addressId}")
    public ResponseEntity<Void> deleteAddressById(@PathVariable("addressId") String addressId){
        addressService.deleteAddress(addressId);
        return ResponseEntity.noContent().build();
    }
}
