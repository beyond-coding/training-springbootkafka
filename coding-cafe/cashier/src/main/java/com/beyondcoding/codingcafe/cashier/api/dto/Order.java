package com.beyondcoding.codingcafe.cashier.api.dto;

import com.beyondcoding.codingcafe.cashier.persistence.domain.ProductKind;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    private Long ticket;

    private String product;

    private ProductKind kind;

}
