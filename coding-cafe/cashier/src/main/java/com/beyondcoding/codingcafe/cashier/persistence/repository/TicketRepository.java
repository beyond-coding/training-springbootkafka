package com.beyondcoding.codingcafe.cashier.persistence.repository;

import com.beyondcoding.codingcafe.cashier.persistence.domain.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Long> {


}
