package com.beyondcoding.codingcafe.cashier.api.rest;

import com.beyondcoding.codingcafe.cashier.api.dto.Purchase;
import com.beyondcoding.codingcafe.cashier.api.event.notifier.Notifier;
import com.beyondcoding.codingcafe.cashier.persistence.domain.Ticket;
import com.beyondcoding.codingcafe.cashier.persistence.repository.TicketRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PurchasesEndpointIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private TicketRepository ticketRepository;

    @SpyBean
    private Notifier notifier;

    @Transactional
    @Test
    public void process() {
        Purchase purchase = new Purchase();
        purchase.setCustomer("Jason");
        purchase.setProducts(Arrays.asList("muffin"));

        Ticket ticket = restTemplate.postForObject("/purchases", purchase, Ticket.class);

        assertNotNull(ticket);
        assertNotNull(ticket.getId());

        Ticket one = ticketRepository.getOne(ticket.getId());

        assertNotNull(one);
        assertEquals("Jason", one.getCustomer());
        assertEquals(2, one.getPrice().intValue());
        assertEquals("muffin", one.getProducts().get(0).getName());

        verify(notifier).notify(ticket);
    }
}