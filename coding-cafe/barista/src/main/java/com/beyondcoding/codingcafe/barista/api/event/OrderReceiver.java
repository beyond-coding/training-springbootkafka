package com.beyondcoding.codingcafe.barista.api.event;

import com.beyondcoding.codingcafe.barista.api.dto.Order;
import com.beyondcoding.codingcafe.barista.configuration.Binding;
import com.beyondcoding.codingcafe.barista.logic.Baristas;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Service;

@Service
@Log
@RequiredArgsConstructor
public class OrderReceiver {

    private final Baristas baristas;

    @StreamListener(Binding.ORDERS)
    void receive(Order order) {
        log.info("Received " + order);
        baristas.prepare(order);
    }

}
