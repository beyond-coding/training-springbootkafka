package com.beyondcoding.codingcafe.barista.api.dto;

import lombok.Data;

@Data
public class Cup {

    private Long ticket;

    private String barista;

    private Beverage beverage;

}

