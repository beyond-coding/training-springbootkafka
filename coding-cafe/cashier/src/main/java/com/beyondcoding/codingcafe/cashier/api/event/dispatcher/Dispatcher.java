package com.beyondcoding.codingcafe.cashier.api.event.dispatcher;

import com.beyondcoding.codingcafe.cashier.api.dto.Order;
import com.beyondcoding.codingcafe.cashier.persistence.domain.ProductKind;

import java.util.List;

public abstract class Dispatcher {

    protected abstract ProductKind getProductKind();

    public void dispatch(List<Order> orders) {
        orders.stream()
                .filter(order -> getProductKind().equals(order.getKind()))
                .forEach(this::dispatch);
    }

    protected abstract void dispatch(Order order);
}
