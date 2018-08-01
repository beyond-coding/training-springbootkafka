package com.beyondcoding.codingcafe.pickupstation.configuration;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface Binding {

    String PURCHASES = "purchases";
    String PRODUCTS = "products";

    @Input(PURCHASES)
    SubscribableChannel purchases();

    @Input(PRODUCTS)
    SubscribableChannel products();

}

