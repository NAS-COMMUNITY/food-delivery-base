package com.example.fooddelivery.service.address;


import com.example.fooddelivery.command.AddressCommand;
import com.example.fooddelivery.model.Address;
import com.example.fooddelivery.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AddressServiceImpl implements AddressService{

    private final AddressRepository addressRepository;
    
    @Override
    public Address createAddress(AddressCommand addressCommand) {
        final Address address = addressRepository.save(Address.create(addressCommand));

        return address;
    }
}
