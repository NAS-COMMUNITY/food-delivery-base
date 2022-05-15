package com.example.fooddelivery.service.order;

import com.example.fooddelivery.command.OrderEntityCommand;
import com.example.fooddelivery.model.OrderEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderService {

    Page<OrderEntity> getAll(Pageable pageable);

    OrderEntity findById(String orderId);

    void deleteOrder(String orderId);
}
