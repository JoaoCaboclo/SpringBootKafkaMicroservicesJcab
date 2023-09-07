package com.jcaboclo.orderservice.bff.service;

import com.jcaboclo.basedomains.dto.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;
import java.util.List;

@Service
public class BFFService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BFFService.class);
    @Autowired
    private RestTemplate restTemplate;

    @Value("${backend.order-api.url}")
    private String backendServiceUrl;

    @GetMapping
    public List<Order> listarOrders() {
        List<Order> orders =  restTemplate.getForObject(backendServiceUrl,List.class);
        LOGGER.info(String.format("List of orders ==> %s", orders.stream().toList()));
        return orders;
    }
}
