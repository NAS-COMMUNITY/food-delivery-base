package com.example.fooddelivery.service.order;


import com.example.fooddelivery.command.OrderEntityCommand;
import com.example.fooddelivery.dto.OrderDto;
import com.example.fooddelivery.exception.BusinessException;
import com.example.fooddelivery.exception.ExceptionFactory;
import com.example.fooddelivery.mapper.OrderMapper;
import com.example.fooddelivery.model.OrderEntity;
import com.example.fooddelivery.repository.OrderRepository;
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

    /*@Override
    public OrderEntity createOrder(OrderEntityCommand orderEntityCommand) {
        log.info("Begin creating order with payload {}", JSONUtil.toJSON(orderEntityCommand));

        final OrderEntity orderEntity = orderRepository.save(OrderEntity.createOne(orderEntityCommand));

        log.info("Creating order with payload {} successfully", JSONUtil.toJSON(orderEntity));
        return orderEntity;
    }*/

    @Override
    public Page<OrderDto> getAll(Pageable pageable) {

        Page<OrderEntity> orderEntities = orderRepository.findAll(pageable);
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
}
