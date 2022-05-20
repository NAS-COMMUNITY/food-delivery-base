package com.example.fooddelivery.service.order;


import com.example.fooddelivery.command.AddressCommand;
import com.example.fooddelivery.command.OrderEntityCommand;
import com.example.fooddelivery.dto.OrderDto;
import com.example.fooddelivery.exception.BusinessException;
import com.example.fooddelivery.exception.ExceptionFactory;
import com.example.fooddelivery.mapper.OrderMapper;
import com.example.fooddelivery.model.Address;
import com.example.fooddelivery.model.Customer;
import com.example.fooddelivery.model.OrderEntity;
import com.example.fooddelivery.repository.AddressRepository;
import com.example.fooddelivery.repository.OrderRepository;
import com.example.fooddelivery.service.address.AddressService;
import com.example.fooddelivery.service.customer.CustomerService;
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
public class OrderServiceImpl implements OrderService{

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final AddressRepository addressRepository;
    private final CustomerService customerService;
    private final AddressService addressService;


    @Override
    public OrderEntity create(final OrderEntityCommand orderEntityCommand){
        final Customer customer = orderEntityCommand.getCustomer() == null ? null : customerService.findById(orderEntityCommand.getCustomer());
        final Address bilAddress = orderEntityCommand.getBillingAddress() == null ? null: addressService.findAddressById(orderEntityCommand.getBillingAddress());
        final Address shipAddress = orderEntityCommand.getShippingAddress() == null ? null : addressService.findAddressById(orderEntityCommand.getShippingAddress());

        final OrderEntity order = orderRepository.save(OrderEntity.createOne(orderEntityCommand, customer, bilAddress, shipAddress));
        return order;
    }

    @Override
    public Page<OrderDto> getAll(Pageable pageable) {

        Page<OrderEntity> orderEntities = orderRepository.findAll(pageable);
        for(OrderEntity order :orderEntities){
            log.info(" order with payload {}", JSONUtil.toJSON(order));
        }
        return orderEntities.map(orderMapper::toOrderDto);
    }

    @Override
    public Set<OrderEntity> findById(Set<String> orderId) {
        /*log.info("Begin fetching Order with id {}", orderId);

        final OrderEntity orderEntity = orderRepository.findById(orderId)
                .orElseThrow(() -> new BusinessException(ExceptionFactory.ORDER_NOT_FOUND.get()));

        log.info("Fetching order with id {} successfully", orderId);*/
        return new HashSet<>(orderRepository.findAllById(orderId));
    }

    @Override
    public OrderEntity valide(String id) {
        log.info("Begin fetching order with id {}", id);
        final OrderEntity orderEntity = orderRepository.findById(id)
                .orElseThrow(() -> new BusinessException(ExceptionFactory.ORDER_NOT_FOUND.get()));
        orderEntity.valide();

        return orderRepository.save(orderEntity);
    }

    @Override
    public OrderEntity reject(String orderId, String why) {
        log.info("Begin fetching order with id {}", orderId);
        final OrderEntity orderEntity = orderRepository.findById(orderId)
                .orElseThrow(() -> new BusinessException(ExceptionFactory.ORDER_NOT_FOUND.get()));

        orderEntity.reject(why);
        return orderRepository.save(orderEntity);
    }
    @Override
    public Address addBillingAddressToOrder(String orderId, AddressCommand addressCommand){
        final OrderEntity orderEntity = orderRepository.findById(orderId)
                .orElseThrow(() -> new BusinessException(ExceptionFactory.ORDER_NOT_FOUND.get()));

        log.info("Begin creating address and adding with payload {} to order with id {} ", JSONUtil.toJSON(addressCommand), orderId);

        final Address address = addressRepository.save(orderEntity.linkToAddress(addressCommand));
        log.info("billing address has been added successfully to order with id {}", orderId);

        return address;
    }
    @Override
    public Address addShippingAddressToOrder(String orderId, AddressCommand addressCommand){
        final OrderEntity orderEntity = orderRepository.findById(orderId)
                .orElseThrow(() -> new BusinessException(ExceptionFactory.ORDER_NOT_FOUND.get()));

        log.info("Begin creating address and adding with payload {} to order with id {} ", JSONUtil.toJSON(addressCommand), orderId);

        final Address address = addressRepository.save(orderEntity.linkToAddress(addressCommand));
        log.info("shipping address has been added successfully to order with id {}", orderId);

        return address;
    }
    @Override
    public OrderEntity update(String orderId, OrderEntityCommand orderEntityCommand) {
        log.info("Begin updating order with id {}", orderId);

        final OrderEntity order = orderRepository.findById(orderId)
                .orElseThrow(() -> new BusinessException(ExceptionFactory.ORDER_NOT_FOUND.get()));
        order.update(orderEntityCommand);
        log.info("Updating order with id {} successfully", orderId);

        return order;
    }
}
