package com.beyondcoding.codingcafe.cashier.api.event.dispatcher;

import com.beyondcoding.codingcafe.cashier.api.dto.Order;
import com.beyondcoding.codingcafe.cashier.persistence.domain.ProductKind;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@Log
@RequiredArgsConstructor
public class FoodstuffDispatcher extends Dispatcher {

    private final MessageChannel foodstuffs;

    @Override
    protected ProductKind getProductKind() {
        return ProductKind.FOODSTUFF;
    }

    @Override
    public void dispatch(Order order) {
        log.info("Dispatching " + order);
        Message<Order> message = MessageBuilder.withPayload(order)
                .build();
        foodstuffs.send(message);
    }
}
