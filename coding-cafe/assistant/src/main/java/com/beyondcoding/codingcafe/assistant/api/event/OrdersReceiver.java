package com.beyondcoding.codingcafe.assistant.api.event;

import com.beyondcoding.codingcafe.assistant.api.dto.Order;
import com.beyondcoding.codingcafe.assistant.configuration.Binding;
import com.beyondcoding.codingcafe.assistant.logic.Assistants;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Service;

@Service
@Log
@RequiredArgsConstructor
public class OrdersReceiver {

    private final Assistants assistants;

    @StreamListener(Binding.ORDERS)
    void process(Order order) {
        log.info("Received " + order);
        assistants.process(order);
    }

}
