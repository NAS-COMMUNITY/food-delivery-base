package com.example.fooddelivery.service.customer;

import com.example.fooddelivery.command.AddressCommand;
import com.example.fooddelivery.command.CustomerCommand;
import com.example.fooddelivery.command.OrderEntityCommand;
import com.example.fooddelivery.dto.CustomerDto;
import com.example.fooddelivery.exception.BusinessException;
import com.example.fooddelivery.exception.ExceptionFactory;
import com.example.fooddelivery.mapper.CustomerMapper;
import com.example.fooddelivery.model.Address;
import com.example.fooddelivery.model.Customer;
import com.example.fooddelivery.model.OrderEntity;
import com.example.fooddelivery.repository.AddressRepository;
import com.example.fooddelivery.repository.CustomerRepository;
import com.example.fooddelivery.repository.OrderRepository;
import com.example.fooddelivery.service.order.OrderService;
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

    private final OrderRepository orderRepository;
    private final CustomerMapper customerMapper;

    private final OrderService orderService;

    @Override
    public Page<CustomerDto> getAll(Pageable pageable){
        Page<Address> addresses = addressRepository.findAll(pageable);
        Page<Customer> customers = customerRepository.findAll(pageable);

        return customers.map(customerMapper::toCustomerDto);
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
    public OrderEntity addOrderToCustomer(String customerId, OrderEntityCommand orderEntityCommand){
        final Customer customer = findById(customerId);
        log.info("Begin creating and adding address with payload {} to customer with id {}", JSONUtil.toJSON(orderEntityCommand), customerId);

        final OrderEntity orderEntity = orderRepository.save(customer.addOrderEntity(orderEntityCommand));

        return orderEntity;
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
    public Customer update(String customerId, CustomerCommand customerCommand) {
        log.info("Begin updating customer with id {}", customerId);

        final Customer customer = findById(customerId);
        customer.update(customerCommand);

        log.info("updating customer with id {} successfully", customer.getId());

        return customer;
    }

    @Override
    public Customer deleteCustomer(String customerId) {
        log.info("Begin deleting customer with id {}", customerId);

        final Customer customer = findById(customerId);

        customer.delete();
        log.info("customer with id {} dleted successfully", customer.getId());

        return customerRepository.save(customer);
    }

    @Override
    public Customer createCustomer(final CustomerCommand customerCommand) {
        log.info("Begin creating customer with payload {}", JSONUtil.toJSON(customerCommand));

        final Customer customer = customerRepository.save(Customer.createOne(customerCommand));
        log.info("Creating Customer with payload {} successfully", JSONUtil.toJSON(customer));

        return customer;
    }
}
