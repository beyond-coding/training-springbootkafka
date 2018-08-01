package com.beyondcoding.onlineshoppingcustomer;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log
public class Customer {

    private final MessageChannel orders;

    public String order(String product) {
        log.warning("CUSTOMER -- Ordering " + product);
        Message<String> message = MessageBuilder.withPayload(product)
                .build();
        orders.send(message);
        return "Your order was placed: " + product;
    }

    @StreamListener(Binding.PRODUCTS)
    public void receive(Product product) {
        log.warning("CUSTOMER -- Received " + product.getName());
    }

}
