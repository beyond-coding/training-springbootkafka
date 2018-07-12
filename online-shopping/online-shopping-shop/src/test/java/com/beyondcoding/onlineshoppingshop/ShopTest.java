package com.beyondcoding.onlineshoppingshop;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.cloud.stream.test.binder.MessageCollector;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.concurrent.BlockingQueue;

import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShopTest {

    @Autowired
    private SubscribableChannel orders;

    @Autowired
    private MessageChannel products;

    @Autowired
    private MessageCollector collector;

    @SpyBean
    private Shop shop;

    @Test
    public void testProcessOrderAndDeliverProduct() throws IOException {
        String order = "Jacket";
        Message<String> request = MessageBuilder.withPayload(order)
                .build();
        orders.send(request);

        verify(shop).process(order);

        BlockingQueue<Message<?>> messages = collector.forChannel(products);

        Object response = messages.poll().getPayload();
        Assert.assertEquals("{\"name\":\"Jacket\"}", response);
    }
}