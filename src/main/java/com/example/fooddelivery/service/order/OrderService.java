package com.example.fooddelivery.service.order;

import com.example.fooddelivery.command.AddressCommand;
import com.example.fooddelivery.command.OrderEntityCommand;
import com.example.fooddelivery.command.FoodItemCommand;
import com.example.fooddelivery.dto.OrderDto;
import com.example.fooddelivery.model.Address;
import com.example.fooddelivery.model.OrderEntity;
import com.example.fooddelivery.model.FoodItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Set;

public interface OrderService {
    Page<OrderDto> getAll(Pageable pageable);
    Set<OrderEntity> findAllById(Set<String> id);
    Address addBillingAddressToOrder(String orderId, AddressCommand addressCommand);
    Address addShippingAddressToOrder(String orderId, AddressCommand addressCommand);
    FoodItem addFoodItem(String orderId, FoodItemCommand foodItemCommand);
    OrderEntity update(String orderId, OrderEntityCommand orderEntityCommand);
    OrderEntity create(final OrderEntityCommand orderEntityCommand);
    OrderEntity findOne(String orderId);
}
