package com.beyondcoding.onlineshoppingcustomer;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface Binding {

    String ORDERS = "orders";

    String PRODUCTS = "products";

    @Output(ORDERS)
    MessageChannel orders();

    @Input(PRODUCTS)
    SubscribableChannel products();

}
