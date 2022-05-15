package com.example.fooddelivery.service.order;


import com.example.fooddelivery.command.OrderEntityCommand;
import com.example.fooddelivery.exception.BusinessException;
import com.example.fooddelivery.exception.ExceptionFactory;
import com.example.fooddelivery.model.OrderEntity;
import com.example.fooddelivery.repository.OrderRepository;
import com.example.fooddelivery.util.JSONUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService{

    private final OrderRepository orderRepository;

    /*@Override
    public OrderEntity createOrder(OrderEntityCommand orderEntityCommand) {
        log.info("Begin creating order with payload {}", JSONUtil.toJSON(orderEntityCommand));

        final OrderEntity orderEntity = orderRepository.save(OrderEntity.createOne(orderEntityCommand));

        log.info("Creating order with payload {} successfully", JSONUtil.toJSON(orderEntity));
        return orderEntity;
    }*/

    @Override
    public Page<OrderEntity> getAll(Pageable pageable) {
        return orderRepository.findAll(pageable);
    }

    @Override
    public OrderEntity findById(String orderId) {
        log.info("Begin fetching Order with id {}", orderId);

        final OrderEntity orderEntity = orderRepository.findById(orderId)
                .orElseThrow(() -> new BusinessException(ExceptionFactory.ORDER_NOT_FOUND.get()));

        log.info("Fetching order with id {} successfully", orderId);

        return orderEntity;
    }

    @Override
    public void deleteOrder(String orderId) {
        log.info("Begin deleting order with id {}", orderId);

        final OrderEntity orderEntity = findById(orderId);

        orderEntity.delete();
        log.info("Deleting order with id {} successfully", orderId);

        orderRepository.save(orderEntity);
    }
}
