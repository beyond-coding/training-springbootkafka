package com.beyondcoding.codingcafe.barista.endpoint;

import com.beyondcoding.codingcafe.barista.api.dto.Cup;
import com.beyondcoding.codingcafe.barista.api.dto.Order;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OrdersEndpointTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void placeWithPathVariables() {
        Cup cup = restTemplate.getForObject("/orders/macchiato/1234", Cup.class);

        assertEquals(new Long(1234), cup.getTicket());
        assertEquals("Sarah", cup.getBarista());
        assertEquals("macchiato", cup.getBeverage().getName());
    }

    @Test
    public void placeWithRequestBody() {
        Order order = new Order();
        order.setTicket(1234l);
        order.setProduct("macchiato");

        Cup cup = restTemplate.postForObject("/orders", order, Cup.class);

        assertEquals(new Long(1234), cup.getTicket());
        assertEquals("Sarah", cup.getBarista());
        assertEquals("macchiato", cup.getBeverage().getName());
    }
}