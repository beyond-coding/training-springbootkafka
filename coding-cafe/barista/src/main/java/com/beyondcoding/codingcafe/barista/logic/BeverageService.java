package com.beyondcoding.codingcafe.barista.logic;

import com.beyondcoding.codingcafe.barista.api.dto.Beverage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BeverageService {

    @Value("#{'${beverage.kinds}'.split(',')}")
    private Set<String> beverages;

    @Value("${beverage.preparation.duration}")
    private Long preparationDuration;

    public List<Beverage> findAll() {
        return beverages.stream()
                .map(Beverage::new)
                .collect(Collectors.toList());
    }

    public Optional<Beverage> prepare(String beverageName) {
        Optional<Beverage> beverage = beverages.stream()
                .filter(e -> e.equalsIgnoreCase(beverageName))
                .map(Beverage::new)
                .findFirst();

        if (!beverage.isPresent()) {
            return Optional.empty();
        }

        waitPreparationDuration();
        return beverage;
    }

    private void waitPreparationDuration() {
        try {
            Thread.sleep(preparationDuration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
