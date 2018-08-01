package com.beyondcoding.codingcafe.assistant.logic;

import com.beyondcoding.codingcafe.assistant.api.dto.Order;
import com.beyondcoding.codingcafe.assistant.api.dto.Plate;
import com.beyondcoding.codingcafe.assistant.api.event.ProductDispatcher;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class Assistants {

    private final Counter counter;

    private final Stocker stocker;

    private final ProductDispatcher dispatcher;

    @Value("${assistant.stock.fetch.duration}")
    private Long duration;

    public Plate process(Order order) {
        Optional<Plate> plate = counter.fetch(order);
        if (!plate.isPresent()) {
            stocker.restock();
            plate = counter.fetch(order);
        }
        waitFetchDuration();
        dispatcher.dispatch(plate.get());
        return plate.get();
    }

    private void waitFetchDuration() {
        try {
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
