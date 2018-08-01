package com.beyondcoding.codingcafe.cashier.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Purchase {

    private Long ticket;

    private String customer;

    private List<String> products = new ArrayList<>();

}