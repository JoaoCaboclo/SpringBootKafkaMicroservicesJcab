package com.jcaboclo.orderservice.controller;

import com.jcaboclo.basedomains.dto.Order;
import com.jcaboclo.basedomains.dto.OrderEvent;
import com.jcaboclo.orderservice.kafka.OrderProducer;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
public class OrderController {

    private OrderProducer OrderProducer;
    public OrderController(com.jcaboclo.orderservice.kafka.OrderProducer orderProducer) {
        OrderProducer = orderProducer;
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

}
