package com.beyondcoding.codingcafe.assistant.logic;

import com.beyondcoding.codingcafe.assistant.api.dto.Order;
import com.beyondcoding.codingcafe.assistant.api.dto.Plate;
import com.beyondcoding.codingcafe.assistant.api.event.ProductDispatcher;
import com.beyondcoding.codingcafe.assistant.persistence.repository.FoodstuffRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class AssistantsTest {

    @Autowired
    private Assistants assistants;

    @SpyBean
    private Stocker stocker;

    @SpyBean
    private Counter counter;

    @SpyBean
    private ProductDispatcher dispatcher;

    @Autowired
    private FoodstuffRepository foodstuffRepository;

    private Long ticket = 1234l;

    @Test
    public void testProcess() {
        String product = "muffin";
        Order order = createOrder(product);

        processSuccessfully(product, order);

        verify(counter).fetch(order);
        verify(stocker, times(0)).restock();

        assertEquals(2, foodstuffRepository.countByName(product));
    }


    @Test
    public void testProcessNeedsRestock() {
        String product = "cookie";
        Order order = createOrder(product);

        IntStream.rangeClosed(1, 3)
                .forEach(e -> processSuccessfully(product, order));

        verify(counter, times(3)).fetch(order);
        verify(stocker, times(0)).restock();
        assertEquals(0, foodstuffRepository.countByName(product));

        processSuccessfully(product, order);

        verify(counter, times(5)).fetch(order);
        verify(stocker, times(1)).restock();
        assertEquals(2, foodstuffRepository.countByName(product));
    }

    private void processSuccessfully(String product, Order order) {
        Plate plate = assistants.process(order);

        assertNotNull(plate);
        assertEquals(ticket, plate.getTicket());
        assertEquals(product, plate.getFoodstuff().getName());
        verify(dispatcher).dispatch(plate);
    }

    private Order createOrder(String product) {
        return Order.builder()
                .ticket(ticket)
                .product(product)
                .build();
    }
}