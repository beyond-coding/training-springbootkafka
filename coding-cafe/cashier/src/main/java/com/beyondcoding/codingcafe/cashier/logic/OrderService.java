package com.beyondcoding.codingcafe.cashier.logic;

import com.beyondcoding.codingcafe.cashier.api.dto.Order;
import com.beyondcoding.codingcafe.cashier.persistence.domain.Product;
import com.beyondcoding.codingcafe.cashier.persistence.domain.Ticket;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class OrderService {

    public List<Order> from(Ticket ticket) {
        List<Product> products = ticket.getProducts();
        return products.stream()
                .map(toOrder(ticket))
                .collect(Collectors.toList());
    }

    private Function<Product, Order> toOrder(Ticket ticket) {
        return product -> Order.builder()
                .ticket(ticket.getId())
                .product(product.getName())
                .kind(product.getKind())
                .build();
    }

}
