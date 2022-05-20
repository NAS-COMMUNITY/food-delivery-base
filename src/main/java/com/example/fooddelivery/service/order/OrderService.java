package com.example.fooddelivery.service.order;

import com.example.fooddelivery.command.AddressCommand;
import com.example.fooddelivery.command.OrderEntityCommand;
import com.example.fooddelivery.dto.OrderDto;
import com.example.fooddelivery.model.Address;
import com.example.fooddelivery.model.OrderEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Set;

public interface OrderService {
    //public OrderEntity createOrder(OrderEntityCommand orderEntityCommand);

    Page<OrderDto> getAll(Pageable pageable);

    public Set<OrderEntity> findById(Set<String> id);

    public OrderEntity valide(String id);

    OrderEntity reject(String orderId, String why);
    public Address addBillingAddressToOrder(String orderId, AddressCommand addressCommand);

    public Address addShippingAddressToOrder(String orderId, AddressCommand addressCommand);

    OrderEntity update(String orderId, OrderEntityCommand orderEntityCommand);
    public OrderEntity create(final OrderEntityCommand orderEntityCommand);
}
