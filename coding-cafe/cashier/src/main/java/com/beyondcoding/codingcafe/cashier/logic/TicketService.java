package com.beyondcoding.codingcafe.cashier.logic;

import com.beyondcoding.codingcafe.cashier.api.dto.Purchase;
import com.beyondcoding.codingcafe.cashier.persistence.domain.Product;
import com.beyondcoding.codingcafe.cashier.persistence.domain.Ticket;
import com.beyondcoding.codingcafe.cashier.persistence.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketService {

    private final ProductService productService;

    private final TicketRepository ticketRepository;

    public List<Ticket> findAll() {
        return ticketRepository.findAll();
    }

    public Ticket process(Purchase purchase) {
        Ticket ticket = createTicket(purchase);
        return ticketRepository.save(ticket);
    }

    private Ticket createTicket(Purchase purchase) {
        List<Product> products = productService.from(purchase.getProducts());
        return Ticket.builder()
                .customer(purchase.getCustomer())
                .products(products)
                .price(calculatePrice(products))
                .timestamp(LocalDateTime.now())
                .build();
    }

    private int calculatePrice(List<Product> products) {
        return products.stream()
                .mapToInt(Product::getPrice)
                .sum();
    }
}
