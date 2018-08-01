package com.beyondcoding.codingcafe.pickupstation.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseStatus {

    private Long ticket;

    private String customer;

    private List<String> remaining;

    private boolean completed;

    public void remove(String product) {
        remaining.remove(product);
        if (remaining.isEmpty()) {
            completed = true;
        }
    }

}
