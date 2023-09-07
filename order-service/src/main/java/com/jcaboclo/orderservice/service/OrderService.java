package com.jcaboclo.orderservice.service;

import com.jcaboclo.basedomains.dto.Order;
import com.jcaboclo.orderservice.kafka.OrderProducer;
import com.jcaboclo.orderservice.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public OrderService() {
     }

    public List<Order> listarOrders() {
        return this.orderRepository.findAll();
    }
}
