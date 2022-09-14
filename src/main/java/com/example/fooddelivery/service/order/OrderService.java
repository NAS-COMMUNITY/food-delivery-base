package com.example.fooddelivery.service.order;

import com.example.fooddelivery.command.*;
import com.example.fooddelivery.dto.OrderDto;
import com.example.fooddelivery.model.Address;
import com.example.fooddelivery.model.OrderEntity;
import com.example.fooddelivery.model.FoodItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface OrderService {
    Page<OrderDto> getAll(Pageable pageable);
    Address addBillingAddressToOrder(String orderId, AddressCommand addressCommand);
    Address addShippingAddressToOrder(String orderId, AddressCommand addressCommand);
    FoodItem addFoodItem(String orderId, FoodItemCommand foodItemCommand);
    OrderEntity update(String orderId, UpdateOrderRequest updateOrderRequest);
    OrderEntity create(final OrderEntityCommand orderEntityCommand);
    OrderEntity findOne(String orderId);
    void removeFoodItemFormOrder(OrderFoodItemRequest orderFoodItemRequest);
}
