package com.beyondcoding.marcopolo.polo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.cloud.stream.test.binder.MessageCollector;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext
public class PoloApplicationTests {

    @Autowired
    private Processor pipe;

    @Autowired
    private MessageCollector messageCollector;

    @Test
    public void testMarcoIsReceivedAndPoloIsSent() {
        Message<PoloApplication.Person> message = MessageBuilder.withPayload(new PoloApplication.Person("Marco"))
                .build();
        pipe.input().send(message);

        Object payload = messageCollector.forChannel(pipe.output())
                .poll()
                .getPayload();

        assertEquals("{\"name\":\"Polo\"}", payload.toString());
    }

}
