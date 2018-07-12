package com.beyondcoding.onlineshoppingshop;

import lombok.extern.java.Log;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;

@Service
@Log
public class Shop {

    @StreamListener(Binding.ORDERS)
    @SendTo(Binding.PRODUCTS)
//    @Transformer(inputChannel = Binding.ORDERS, outputChannel = Binding.PRODUCTS)
    public Product process(String order) {
        log.warning("SHOP -- Received order " + order);

        waitSeconds(5);

        log.warning("SHOP -- Delivering order " + order);

        return new Product(order);
    }

    private void waitSeconds(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
