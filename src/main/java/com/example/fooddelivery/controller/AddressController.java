package com.example.fooddelivery.controller;


import com.example.fooddelivery.command.AddressCommand;
import com.example.fooddelivery.dto.AddressDto;
import com.example.fooddelivery.mapper.AddressMapper;
import com.example.fooddelivery.model.Address;
import com.example.fooddelivery.service.address.AddressService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @ApiOperation("This method return all addresses")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Page<AddressDto>> getAll(Pageable pageable){
        return ResponseEntity.ok(addressService.getAll(pageable));
    }
    @PutMapping("/{addressId}")
    @ApiOperation("This method will be update an address by id")
    @PreAuthorize("#addressId == authentication.principal.userId or hasAnyRole('USER' or 'ADMIN')")
    public ResponseEntity<AddressDto> updateAddress(@PathVariable("addressId") String addressId,
                                                    @RequestBody AddressCommand addressCommand){
        final Address address = addressService.update(addressId, addressCommand);

        return ResponseEntity.ok(addressMapper.toAddressDto(address));
    }
    @DeleteMapping("/{addressId}")
    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation("This method will be delete an address by id")
    public ResponseEntity<Void> deleteAddressById(@PathVariable("addressId") String addressId){
        addressService.deleteAddress(addressId);
        return ResponseEntity.noContent().build();
    }
}
