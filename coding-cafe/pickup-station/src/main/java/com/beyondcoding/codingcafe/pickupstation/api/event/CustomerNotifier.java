package com.beyondcoding.codingcafe.pickupstation.api.event;

import com.beyondcoding.codingcafe.pickupstation.domain.PurchaseStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

@Service
@Log
@RequiredArgsConstructor
public class CustomerNotifier {

    public void notify(PurchaseStatus purchaseStatus) {
        log.info("Notifying customer for " + purchaseStatus);
    }
}
