package com.beyondcoding.codingcafe.barista.api.rest;

import com.beyondcoding.codingcafe.barista.api.dto.Cup;
import com.beyondcoding.codingcafe.barista.api.dto.Order;
import com.beyondcoding.codingcafe.barista.logic.Baristas;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrdersEndpoint {

    private final Baristas baristas;

    @GetMapping
    List<Cup> findAll() {
        return baristas.getPreparedCups();
    }

    @GetMapping("/{beverageName}/{ticket}")
    Cup place(@PathVariable String beverageName, @PathVariable Long ticket) {
        Order order = new Order();
        order.setTicket(ticket);
        order.setProduct(beverageName);
        return baristas.prepare(order).get();
    }

    @PostMapping
    Cup place(@RequestBody Order order) {
        return baristas.prepare(order).get();
    }

}
