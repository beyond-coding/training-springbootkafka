package com.beyondcoding.codingcafe.assistant.logic;

import com.beyondcoding.codingcafe.assistant.api.dto.Order;
import com.beyondcoding.codingcafe.assistant.api.dto.Plate;
import com.beyondcoding.codingcafe.assistant.persistence.domain.Foodstuff;
import com.beyondcoding.codingcafe.assistant.persistence.repository.FoodstuffRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class Counter {

    private final FoodstuffRepository foodstuffRepository;

    public Optional<Plate> fetch(Order order) {
        Optional<Plate> plate = preparePlate(order);
        removeFoodstuffFromCounter(plate);
        return plate;
    }

    private Optional<Plate> preparePlate(Order order) {
        List<Foodstuff> foodstuffs = foodstuffRepository.findByName(order.getProduct());
        return foodstuffs.stream()
                .map(toPlate(order))
                .findFirst();
    }

    private Function<Foodstuff, Plate> toPlate(Order order) {
        return foodstuff -> Plate.builder()
                .ticket(order.getTicket())
                .foodstuff(foodstuff)
                .build();
    }

    private void removeFoodstuffFromCounter(Optional<Plate> plate) {
        if (plate.isPresent()) {
            Foodstuff foodstuff = plate.get().getFoodstuff();
            foodstuffRepository.deleteById(foodstuff.getId());
        }
    }
}
