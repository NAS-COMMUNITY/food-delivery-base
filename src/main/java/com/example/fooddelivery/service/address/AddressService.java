package com.example.fooddelivery.service.address;

import com.example.fooddelivery.command.AddressCommand;
import com.example.fooddelivery.model.Address;

public interface AddressService {

    Address createAddress(AddressCommand addressCommand);
}
