package com.jcaboclo.stockservice.kafka;

import com.jcaboclo.basedomains.dto.OrderEvent;
import com.jcaboclo.stockservice.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
@Service
public class OrderConsumer {

    private final OrderRepository orderRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderConsumer.class);

    public OrderConsumer(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @KafkaListener(
            topics = "${spring.kafka.topic.name}",
            groupId = "${spring.kafka.consumer.group-id}"
    )
    public void consume(OrderEvent event) {
        LOGGER.info(String.format("Order event received in stock-service ==> %s", event.toString()));

        // todo
        //   Save the order event into the database
        orderRepository.save(event.getOrder());
    }


}
