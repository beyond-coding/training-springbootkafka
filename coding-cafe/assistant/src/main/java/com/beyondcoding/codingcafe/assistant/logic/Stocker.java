package com.beyondcoding.codingcafe.assistant.logic;

import com.beyondcoding.codingcafe.assistant.persistence.domain.Foodstuff;
import com.beyondcoding.codingcafe.assistant.persistence.repository.FoodstuffRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class Stocker {

    private final FoodstuffRepository foodstuffRepository;

    @Value("#{'${assistant.product.kinds}'.split(',')}")
    private List<String> productKinds;

    @Value("${assistant.product.amount}")
    private Integer amount;

    @Value("${assistant.stock.restock.duration}")
    private Long duration;

    public void stock() {
        productKinds.forEach(this::restock);
    }

    public void restock() {
        waitRestockDuration();
        productKinds.forEach(this::restock);
    }

    private void waitRestockDuration() {
        try {
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void restock(String name) {
        int stock = foodstuffRepository.countByName(name);
        int restock = amount - stock;
        IntStream.range(0, restock)
                .mapToObj(e -> name)
                .map(Foodstuff::new)
                .forEach(foodstuffRepository::save);
    }

}
