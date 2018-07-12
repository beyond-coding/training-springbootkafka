package com.beyondcoding.onlineshoppingcustomer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.cloud.stream.test.binder.MessageCollector;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.BlockingQueue;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.verify;
import static org.springframework.cloud.stream.test.matcher.MessageQueueMatcher.receivesPayloadThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OrdersEndpointTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private MessageChannel orders;

    @Autowired
    private MessageCollector collector;

    @SpyBean
    private Customer customer;

    @Test
    public void testOrderIsSent() {
        String order = "skateboard";
        String response = restTemplate.getForObject("/orders/" + order, String.class);
        System.out.println(response);
        assertThat(response, is("Your order was placed: " + order));

        BlockingQueue<Message<?>> messages = collector.forChannel(orders);
        assertThat(messages, receivesPayloadThat(is(order)));

        verify(customer).order(order);
    }

}