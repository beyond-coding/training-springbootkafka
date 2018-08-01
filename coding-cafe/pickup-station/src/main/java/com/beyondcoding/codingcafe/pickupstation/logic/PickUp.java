package com.beyondcoding.codingcafe.pickupstation.logic;

import com.beyondcoding.codingcafe.pickupstation.api.dto.Product;
import com.beyondcoding.codingcafe.pickupstation.api.dto.Purchase;
import com.beyondcoding.codingcafe.pickupstation.api.event.CustomerNotifier;
import com.beyondcoding.codingcafe.pickupstation.domain.PurchaseStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PickUp {

    private final CustomerNotifier customerNotifier;

    private Map<Long, PurchaseStatus> status = new HashMap<>();

    public List<PurchaseStatus> getStatus() {
        return status.entrySet().stream()
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
    }

    public PurchaseStatus receive(Purchase purchase) {
        Long ticket = purchase.getTicket();
        status.put(ticket, toPurchaseStatus(purchase));
        return status.get(ticket);
    }

    private PurchaseStatus toPurchaseStatus(Purchase purchase) {
        return PurchaseStatus.builder()
                .ticket(purchase.getTicket())
                .customer(purchase.getCustomer())
                .remaining(purchase.getProducts())
                .build();
    }

    public PurchaseStatus receive(Product product) {
        PurchaseStatus purchaseStatus = status.get(product.getTicket());
        if (purchaseStatus == null) {
            return null;
        }
        update(purchaseStatus, product);
        return purchaseStatus;
    }

    private void update(PurchaseStatus purchaseStatus, Product product) {
        purchaseStatus.remove(product.getName());
        notify(purchaseStatus);
    }

    private void notify(PurchaseStatus purchaseStatus) {
        if (purchaseStatus.isCompleted()) {
            customerNotifier.notify(purchaseStatus);
        }
    }

}
