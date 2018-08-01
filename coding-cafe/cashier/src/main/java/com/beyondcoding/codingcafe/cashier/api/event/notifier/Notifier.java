package com.beyondcoding.codingcafe.cashier.api.event.notifier;

import com.beyondcoding.codingcafe.cashier.api.dto.Purchase;
import com.beyondcoding.codingcafe.cashier.persistence.domain.Product;
import com.beyondcoding.codingcafe.cashier.persistence.domain.Ticket;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@Log
@RequiredArgsConstructor
public class Notifier {

    private final MessageChannel purchases;

    public void notify(Ticket ticket) {
        Purchase purchase = createPurchase(ticket);
        notify(purchase);
    }

    private Purchase createPurchase(Ticket ticket) {
        return Purchase.builder()
                .ticket(ticket.getId())
                .customer(ticket.getCustomer())
                .products(
                        ticket.getProducts().stream()
                                .map(Product::getName)
                                .collect(Collectors.toList())
                )
                .build();
    }

    private void notify(Purchase purchase) {
        log.info("Notifying " + purchase);
        Message<Purchase> message = MessageBuilder.withPayload(purchase)
                .build();
        purchases.send(message);
    }
}
