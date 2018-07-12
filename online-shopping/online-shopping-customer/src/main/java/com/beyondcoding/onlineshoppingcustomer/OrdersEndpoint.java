package com.beyondcoding.onlineshoppingcustomer;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrdersEndpoint {

    private final Customer customer;

    @GetMapping("/orders/{product}")
    public String order(@PathVariable String product) {
        customer.order(product);
        return "Your order was placed: " + product;
    }

}
