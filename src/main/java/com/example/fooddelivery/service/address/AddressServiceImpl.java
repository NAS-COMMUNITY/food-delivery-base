package com.example.fooddelivery.service.address;


import com.example.fooddelivery.command.AddressCommand;
import com.example.fooddelivery.dto.AddressDto;
import com.example.fooddelivery.exception.BusinessException;
import com.example.fooddelivery.exception.ExceptionFactory;
import com.example.fooddelivery.mapper.AddressMapper;
import com.example.fooddelivery.model.Address;
import com.example.fooddelivery.repository.AddressRepository;
import com.example.fooddelivery.util.JSONUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class AddressServiceImpl implements AddressService{

    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;

    @Override
    public Page<AddressDto> getAll(Pageable pageable) {
        Page<Address> addresses = addressRepository.findAll(pageable);
        return addresses.map(addressMapper::toAddressDto);
    }

    @Override
    public Address update(String addressId, AddressCommand addressCommand) {
        log.info("Begin updating address with payload {}", JSONUtil.toJSON(addressCommand));

        final Address address = findAddressById(addressId);
        address.update(addressCommand);
        log.info("Address with payload {} updated successfully", JSONUtil.toJSON(address));

        return addressRepository.save(address);
    }

    @Override
    public void deleteAddress(String addressId) {
        log.info("Begin deleting address with id {}", addressId);

        final Address address = findAddressById(addressId);

        address.delete();
        log.info("Address with id {}  deleted successfully", addressId);

        addressRepository.save(address);
    }

    @Override
    public Address findAddressById(String addressId) {
        log.info("Begin fetching address with id {}", addressId);

        final Address address = addressRepository.findById(addressId)
                .orElseThrow(() -> new BusinessException(ExceptionFactory.ADDRESS_NOT_FOUND.get()));
        log.info("Address with id {} fetched successfully", addressId);

        return address;
    }

    @Override
    public Set<Address> findById(Set<String> id) {
        return new HashSet<>(addressRepository.findAllById(id));
    }
}
