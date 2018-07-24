package com.beyondcoding.data.fakebook;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/persons")
@RequiredArgsConstructor
public class PersonsEndpoint {

    private final Persons persons;

    @GetMapping
    List<Person> all() {
        return persons.findAll();
    }

    @PostMapping
    Person add(@RequestBody Person person) {
        return persons.save(person);
    }

    @PutMapping("/{id1}/{id2}/friend")
    Boolean friend(@PathVariable Long id1, @PathVariable Long id2) {
        persons.friend(id1, id2);
        return true;
    }

    @PutMapping("/{id1}/{id2}/unfriend")
    Boolean unfriend(@PathVariable Long id1, @PathVariable Long id2) {
        persons.unfriend(id1, id2);
        return true;
    }

    @GetMapping("/{name}")
    Person byName(@PathVariable String name) {
        return persons.byName(name).orElse(null);
    }

}
