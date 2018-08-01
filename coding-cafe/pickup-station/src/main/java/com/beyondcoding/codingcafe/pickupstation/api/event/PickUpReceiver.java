package com.beyondcoding.codingcafe.pickupstation.api.event;

import com.beyondcoding.codingcafe.pickupstation.api.dto.Product;
import com.beyondcoding.codingcafe.pickupstation.api.dto.Purchase;
import com.beyondcoding.codingcafe.pickupstation.configuration.Binding;
import com.beyondcoding.codingcafe.pickupstation.logic.PickUp;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Service;

@Service
@Log
@RequiredArgsConstructor
public class PickUpReceiver {

    private final PickUp pickUp;

    @StreamListener(Binding.PURCHASES)
    void receive(Purchase purchase) {
        log.info("Received " + purchase);
        pickUp.receive(purchase);
    }

    @StreamListener(Binding.PRODUCTS)
    void receive(Product product) {
        log.info("Received " + product);
        pickUp.receive(product);
    }

}
