package com.beyondcoding.marcopolo.marco;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.cloud.stream.test.binder.MessageCollector;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext
public class MarcoApplicationTests {

    @Autowired
    private Processor pipe;

    @Autowired
    private MessageCollector messageCollector;

    @Test
    public void testMarcoIsSentAutomatically() {
        Object payload = messageCollector.forChannel(pipe.output())
                .poll()
                .getPayload();

        assertEquals("{\"name\":\"Marco\"}", payload.toString());
    }

}
