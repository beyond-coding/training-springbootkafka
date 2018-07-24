package com.beyondcoding.data.todos;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface TodoRepository extends MongoRepository<Todo, String> {

    Optional<Todo> findByName(String name);

}
