package com.jcaboclo.orderservice.repository;

import com.jcaboclo.basedomains.dto.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepository extends MongoRepository<Order, String>{
}
