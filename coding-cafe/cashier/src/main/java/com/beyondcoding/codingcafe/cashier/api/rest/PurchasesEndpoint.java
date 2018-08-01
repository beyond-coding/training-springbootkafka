package com.beyondcoding.codingcafe.cashier.api.rest;

import com.beyondcoding.codingcafe.cashier.api.dto.Purchase;
import com.beyondcoding.codingcafe.cashier.logic.PurchaseService;
import com.beyondcoding.codingcafe.cashier.persistence.domain.Ticket;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/purchases")
@RequiredArgsConstructor
public class PurchasesEndpoint {

    private final PurchaseService purchaseService;

    @GetMapping
    List<Ticket> all() {
        return purchaseService.findTickets();
    }

    @PostMapping
    Ticket process(@RequestBody Purchase purchase) {
        return purchaseService.process(purchase);
    }

}
