package com.beyondcoding.codingcafe.assistant.api.rest;

import com.beyondcoding.codingcafe.assistant.persistence.domain.Foodstuff;
import com.beyondcoding.codingcafe.assistant.persistence.repository.FoodstuffRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductsEndpoint {

    private final FoodstuffRepository foodstuffRepository;

    public ProductsEndpoint(FoodstuffRepository foodstuffRepository) {
        this.foodstuffRepository = foodstuffRepository;
    }

    @GetMapping
    List<Foodstuff> findAll() {
        return foodstuffRepository.findAll();
    }

}
