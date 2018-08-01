package com.beyondcoding.codingcafe.barista.logic;

import com.beyondcoding.codingcafe.barista.api.dto.Beverage;
import com.beyondcoding.codingcafe.barista.api.dto.Cup;
import com.beyondcoding.codingcafe.barista.api.dto.Order;
import com.beyondcoding.codingcafe.barista.api.event.ProductDispatcher;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class Baristas {

    private final BeverageService beverageService;

    private final ProductDispatcher dispatcher;

    @Value("#{'${barista.names}'.split(',')}")
    private List<String> baristas;

    private List<Cup> preparedCups = new ArrayList<>();

    public Optional<Cup> prepare(Order order) {
        Optional<Beverage> beverage = beverageService.prepare(order.getProduct());
        if (!beverage.isPresent()) {
            return Optional.empty();
        }
        Cup cup = prepareCup(order, beverage);
        preparedCups.add(cup);
        dispatcher.dispatch(cup);
        return Optional.of(cup);
    }

    private Cup prepareCup(Order order, Optional<Beverage> beverage) {
        Cup cup = new Cup();
        cup.setTicket(order.getTicket());
        cup.setBarista(getNextBarista());
        cup.setBeverage(beverage.get());
        return cup;
    }

    private String getNextBarista() {
        List<String> names = new ArrayList<>(baristas);
        Collections.shuffle(names);
        return names.get(0);
    }

    public List<Cup> getPreparedCups() {
        return new ArrayList<>(preparedCups);
    }
}
