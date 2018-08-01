package com.beyondcoding.codingcafe.barista.api.event;

import com.beyondcoding.codingcafe.barista.api.dto.Cup;
import com.beyondcoding.codingcafe.barista.api.dto.Product;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@Log
@RequiredArgsConstructor
public class ProductDispatcher {

    private final MessageChannel products;

    public void dispatch(Cup order) {
        Product product = parse(order);
        dispatch(product);
    }

    private Product parse(Cup order) {
        return Product.builder()
                .ticket(order.getTicket())
                .name(order.getBeverage().getName())
                .build();
    }

    private void dispatch(Product product) {
        log.info("Dispatching " + product);
        Message<Product> message = MessageBuilder.withPayload(product)
                .build();
        products.send(message);
    }
}
