package com.beyondcoding.onlineshoppingshop;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface Binding {

    String PRODUCTS = "products";

    String ORDERS = "orders";

    @Output(PRODUCTS)
    MessageChannel products();

    @Input(ORDERS)
    SubscribableChannel orders();

}
