package com.jcaboclo.orderservice.bff.controller;

import com.jcaboclo.basedomains.dto.Order;
import com.jcaboclo.orderservice.bff.service.BFFService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/v1/dadosorder")
public class BFFController {

    private final BFFService bffService;
    public BFFController(BFFService bffService) {
        this.bffService = bffService;
    }

    @GetMapping
    public List<Order> listarOrders() {
        return bffService.listarOrders();
    }
}
