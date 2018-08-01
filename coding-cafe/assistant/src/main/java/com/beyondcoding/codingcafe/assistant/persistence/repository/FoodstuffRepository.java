package com.beyondcoding.codingcafe.assistant.persistence.repository;

import com.beyondcoding.codingcafe.assistant.persistence.domain.Foodstuff;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface FoodstuffRepository extends MongoRepository<Foodstuff, String> {

    List<Foodstuff> findByName(String name);

    int countByName(String name);

}
