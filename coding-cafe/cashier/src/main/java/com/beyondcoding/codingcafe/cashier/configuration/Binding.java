package com.beyondcoding.codingcafe.cashier.configuration;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface Binding {

    @Output
    MessageChannel purchases();

    @Output
    MessageChannel beverages();

    @Output
    MessageChannel foodstuffs();

}
