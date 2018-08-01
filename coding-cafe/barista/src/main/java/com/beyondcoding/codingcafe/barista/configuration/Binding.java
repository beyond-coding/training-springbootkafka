package com.beyondcoding.codingcafe.barista.configuration;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface Binding {

    String ORDERS = "orders";

    @Input(ORDERS)
    SubscribableChannel orders();

    @Output
    MessageChannel products();

}
