package com.beyondcoding.codingcafe.assistant.api.rest;

import com.beyondcoding.codingcafe.assistant.api.dto.Order;
import com.beyondcoding.codingcafe.assistant.api.dto.Plate;
import com.beyondcoding.codingcafe.assistant.logic.Assistants;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrdersEndpoint {

    private final Assistants assistants;

    @PostMapping
    Plate process(@RequestBody Order order) {
        return assistants.process(order);
    }

}
