package com.jcaboclo.orderservice.controller;

import com.jcaboclo.basedomains.dto.Order;
import com.jcaboclo.basedomains.dto.OrderEvent;
import com.jcaboclo.orderservice.kafka.OrderProducer;
import com.jcaboclo.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
public class OrderController {

    private OrderProducer OrderProducer;
    @Autowired
    private final OrderService orderService;

    public OrderController(com.jcaboclo.orderservice.kafka.OrderProducer orderProducer,
                           OrderService orderService) {
        OrderProducer = orderProducer;
        this.orderService = orderService;
    }

    @PostMapping("/orders")
    public String placeOrder(@RequestBody Order order) {

        order.setOrderId(UUID.randomUUID().toString());
        OrderEvent orderEvent = new OrderEvent();
        orderEvent.setStatus("PENDING");
        orderEvent.setMessage("Order status is in pending state");
        orderEvent.setOrder(order);

        OrderProducer.sendMessage(orderEvent);

        return "Order placed successfully....";

    }

    @GetMapping("/orders")
    public List<Order> listarOrders() {

        return orderService.listarOrders();
    }

}
