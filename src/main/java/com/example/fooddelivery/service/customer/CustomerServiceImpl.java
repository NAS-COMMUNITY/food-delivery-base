package com.example.fooddelivery.service.customer;

import com.example.fooddelivery.command.AddressCommand;
import com.example.fooddelivery.command.CustomerCommand;
import com.example.fooddelivery.exception.BusinessException;
import com.example.fooddelivery.exception.ExceptionFactory;
import com.example.fooddelivery.model.Address;
import com.example.fooddelivery.model.Customer;
import com.example.fooddelivery.repository.AddressRepository;
import com.example.fooddelivery.repository.CustomerRepository;
import com.example.fooddelivery.service.CustomerService;
import com.example.fooddelivery.util.JSONUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Set;


@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final AddressRepository addressRepository;


    @Override
    public Page<Customer> getAll(Pageable pageable){
        return customerRepository.findAll(pageable);
    }

    @Override
    public Address addAddressToCustomer(String customerId, AddressCommand addressCommand) {
        final Customer customer = findById(customerId);
        log.info("Begin creating and adding address with payload {} to customer with id {}", JSONUtil.toJSON(addressCommand), customerId);

        final Address address = addressRepository.save(customer.addAddress(addressCommand));
        log.info("New address has been added successfully to customer with id {}", customerId);

        return address;
    }

    @Override
    public Customer findById(String customerId) {
        log.info("Begin fetching customer with id {}", customerId);
        Customer customer = customerRepository.findById(customerId).orElseThrow(() ->
                new BusinessException(ExceptionFactory.INVALID_PAYLOAD.get()));

        log.info("Customer with id {} fetched successfully", customerId);

        return customer;
    }

    @Override
    public Customer createCustomer(CustomerCommand customerCommand) {
        log.info("Begin creating customer with payload {}", JSONUtil.toJSON(customerCommand));


        final Set<AddressCommand> addresses = customerCommand.getAddressCommands();
        log.info("address : {}", JSONUtil.toJSON(addresses));
        final Customer customer = customerRepository.save(Customer.createOne(customerCommand, addresses));
        log.info("Creating Customer with payload {} successfully", JSONUtil.toJSON(customer));

        return customer;
    }
}
