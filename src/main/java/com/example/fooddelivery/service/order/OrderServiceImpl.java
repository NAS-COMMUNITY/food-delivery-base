package com.example.fooddelivery.service.order;


import com.example.fooddelivery.command.*;
import com.example.fooddelivery.dto.OrderDto;
import com.example.fooddelivery.exception.BusinessException;
import com.example.fooddelivery.exception.ExceptionFactory;
import com.example.fooddelivery.dto.mapper.OrderMapper;
import com.example.fooddelivery.model.Address;
import com.example.fooddelivery.model.FoodItem;
import com.example.fooddelivery.model.OrderEntity;
import com.example.fooddelivery.repository.AddressRepository;
import com.example.fooddelivery.repository.OrderRepository;
import com.example.fooddelivery.repository.FoodItemRepository;
import com.example.fooddelivery.service.address.AddressService;
import com.example.fooddelivery.service.foodItem.FoodItemService;
import com.example.fooddelivery.util.JSONUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class OrderServiceImpl implements OrderService{

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final AddressRepository addressRepository;
    private final FoodItemService foodItemService;
    private final AddressService addressService;
    private final FoodItemRepository foodItemRepository;

    @Override
    public Page<OrderDto> getAll(Pageable pageable) {

        log.info("Begin fetching orders");
        Page<OrderEntity> orderEntities = orderRepository.findAll(pageable);
        log.info("Orders with size {} fetched successfully", orderEntities.getTotalElements());
        return orderEntities.map(orderMapper::toOrderDto);
    }
    @Override
    @Transactional
    public OrderEntity create(final OrderEntityCommand orderEntityCommand){
        final Address bilAddress = orderEntityCommand.getBillingAddress() == null ? null: addressService.findAddressById(orderEntityCommand.getBillingAddress());
        final Address shipAddress = orderEntityCommand.getShippingAddress() == null ? null : addressService.findAddressById(orderEntityCommand.getShippingAddress());
        log.info("Begin creating order with payload {}", JSONUtil.toJSON(orderEntityCommand));
        final OrderEntity order = orderRepository.save(OrderEntity.createOne(orderEntityCommand, bilAddress, shipAddress));
        log.info("Order with id {} creating successfully", order.getId());
        return order;
    }
    @Override
    public OrderEntity findOne(String orderId) {
        log.info("Begin fetching order with id {}", orderId);
        final OrderEntity order = orderRepository.findById(orderId)
                .orElseThrow(() -> new BusinessException(ExceptionFactory.ORDER_NOT_FOUND.get()));
        log.info("Order with id {} fetched successfully", order.getId());
        return order;
    }
    @Override
    public void removeFoodItemFormOrder(OrderFoodItemRequest orderFoodItemRequest) {
        final FoodItem foodItem = foodItemService.findOneFoodItem(orderFoodItemRequest.getFoodItemId());
        final OrderEntity orderEntity = findOne(orderFoodItemRequest.getOrderId());
        log.info("Begin removing food Item with id {} from order with id {}", foodItem.getId(), orderEntity.getId());
        orderEntity.removeFoodItem(foodItem);
        log.info("Food Item with id {} removed successfully from order with id {}", foodItem.getId(), orderEntity.getId());
        foodItem.delete();
    }
    @Override
    @Transactional
    public Address addBillingAddressToOrder(String orderId, AddressCommand addressCommand){
        final OrderEntity orderEntity = orderRepository.findById(orderId)
                .orElseThrow(() -> new BusinessException(ExceptionFactory.ORDER_NOT_FOUND.get()));

        log.info("Begin creating address and adding with payload {} to order with id {} ", JSONUtil.toJSON(addressCommand), orderId);
        final Address address = addressRepository.save(Address.create(addressCommand));
        orderEntity.linkToBillingAddress(address);
        log.info("billing address has been added successfully to order with id {}", orderId);

        return address;
    }
    @Override
    @Transactional
    public Address addShippingAddressToOrder(String orderId, AddressCommand addressCommand){
        final OrderEntity orderEntity = orderRepository.findById(orderId)
                .orElseThrow(() -> new BusinessException(ExceptionFactory.ORDER_NOT_FOUND.get()));

        log.info("Begin creating address and adding with payload {} to order with id {} ", JSONUtil.toJSON(addressCommand), orderId);
        final Address address = addressRepository.save(Address.create(addressCommand));
        orderEntity.linkToShippingAddress(address);
        log.info("shipping address has been added successfully to order with id {}", orderId);

        return address;
    }
    @Override
    @Transactional
    public FoodItem addFoodItem(String orderId, FoodItemCommand foodItemCommand) {
        final OrderEntity order = findOne(orderId);
        log.info("Begin adding food Item with payload {} to order with id {}", JSONUtil.toJSON(foodItemCommand), order.getId());
        final FoodItem foodItem = foodItemRepository.save(order.addFoodItem(foodItemCommand));
        log.info("Food Item with id {} added successfully to order with id {}", foodItem.getId(), order.getId());
        return foodItem;
    }
    @Override
    public OrderEntity update(String orderId, UpdateOrderRequest updateOrderRequest) {
        log.info("Begin updating order with id {}", orderId);

        final Address shippingAddress = addressService.findAddressById(updateOrderRequest.getShippingAddressId());
        final Address billingAddress = addressService.findAddressById(updateOrderRequest.getBillingAddressId());

        final OrderEntity order = orderRepository.findById(orderId)
                .orElseThrow(() -> new BusinessException(ExceptionFactory.ORDER_NOT_FOUND.get()));
        order.update(billingAddress, shippingAddress);
        log.info("Updating order with id {} successfully", orderId);

        return order;
    }
}
