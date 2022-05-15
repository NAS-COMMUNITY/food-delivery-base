package com.example.fooddelivery.controller;



import com.example.fooddelivery.dto.OrderDto;
import com.example.fooddelivery.model.OrderEntity;
import com.example.fooddelivery.service.order.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {


    private final OrderService orderService;


    @GetMapping("/all")
    public ResponseEntity<Page<OrderDto>> getAll(Pageable pageable){
        return ResponseEntity.ok(orderService.getAll(pageable));
    }
}