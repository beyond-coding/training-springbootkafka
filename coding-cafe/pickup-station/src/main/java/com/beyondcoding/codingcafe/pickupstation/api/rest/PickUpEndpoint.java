package com.beyondcoding.codingcafe.pickupstation.api.rest;

import com.beyondcoding.codingcafe.pickupstation.api.dto.Product;
import com.beyondcoding.codingcafe.pickupstation.api.dto.Purchase;
import com.beyondcoding.codingcafe.pickupstation.domain.PurchaseStatus;
import com.beyondcoding.codingcafe.pickupstation.logic.PickUp;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PickUpEndpoint {

    private final PickUp pickUp;

    @GetMapping("/purchases")
    List<PurchaseStatus> all() {
        return pickUp.getStatus();
    }

    @PostMapping("/purchases")
    PurchaseStatus receive(@RequestBody Purchase purchase) {
        return pickUp.receive(purchase);
    }

    @PostMapping("/products")
    PurchaseStatus receive(@RequestBody Product product) {
        return pickUp.receive(product);
    }

}
