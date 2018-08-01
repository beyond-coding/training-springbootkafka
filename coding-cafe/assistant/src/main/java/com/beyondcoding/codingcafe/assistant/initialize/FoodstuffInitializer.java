package com.beyondcoding.codingcafe.assistant.initialize;

import com.beyondcoding.codingcafe.assistant.logic.Stocker;
import com.beyondcoding.codingcafe.assistant.persistence.repository.FoodstuffRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FoodstuffInitializer {

    @Bean
    ApplicationRunner initializeFoodstuffs(FoodstuffRepository foodstuffRepository, Stocker stocker) {
        return args -> {
            foodstuffRepository.deleteAll();
            stocker.stock();
        };
    }

}
