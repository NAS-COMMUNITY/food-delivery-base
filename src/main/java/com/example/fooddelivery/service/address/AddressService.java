package com.example.fooddelivery.service.address;

import com.example.fooddelivery.command.AddressCommand;
import com.example.fooddelivery.model.Address;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AddressService {

    Page<Address> getAll(Pageable pageable);
    Address update(String addressId, AddressCommand addressCommand);

    void deleteAddress(String addressId);

    Address findAddressById(String addressId);
}
