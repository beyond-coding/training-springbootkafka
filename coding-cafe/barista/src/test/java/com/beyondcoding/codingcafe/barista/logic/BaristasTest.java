package com.beyondcoding.codingcafe.barista.logic;

import com.beyondcoding.codingcafe.barista.api.dto.Cup;
import com.beyondcoding.codingcafe.barista.api.dto.Order;
import com.beyondcoding.codingcafe.barista.api.event.ProductDispatcher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BaristasTest {

    @Autowired
    private Baristas baristas;

    @SpyBean
    private ProductDispatcher dispatcher;

    @Test
    public void testBeverageIsPrepared() {
        Order order = new Order();
        order.setTicket(1234l);
        order.setProduct("capuccino");

        Optional<Cup> cup = baristas.prepare(order);

        assertTrue(cup.isPresent());
        assertEquals("capuccino", cup.get().getBeverage().getName());
        assertEquals("Sarah", cup.get().getBarista());
        verify(dispatcher).dispatch(any(Cup.class));
    }

    @Test
    public void testBeverateIsNotPrepared() {
        Order order = new Order();
        order.setTicket(1234l);
        order.setProduct("espresso");

        Optional<Cup> cup = baristas.prepare(order);

        assertFalse(cup.isPresent());
        verify(dispatcher, times(0)).dispatch(any(Cup.class));
    }
}