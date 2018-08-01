package com.beyondcoding.codingcafe.pickupstation.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Purchase {

    private Long ticket;

    private String customer;

    private List<String> products;

}
