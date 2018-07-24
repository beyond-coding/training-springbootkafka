package com.beyondcoding.data.fakebook;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class Persons {

    private final PersonRepository personRepository;

    public List<Person> findAll() {
        return personRepository.findAll();
    }

    public Person save(Person person) {
        return personRepository.save(person);
    }

    public void friend(Long id1, Long id2) {
        Optional<Person> person1 = personRepository.findById(id1);
        Optional<Person> person2 = personRepository.findById(id2);
        if (eitherDoesNotExist(person1, person2)) {
            return;
        }
        friend(person1.get(), person2.get());
    }

    private boolean eitherDoesNotExist(Optional<Person> person1, Optional<Person> person2) {
        return !person1.isPresent() || !person2.isPresent();
    }

    private void friend(Person person1, Person person2) {
        person1.getFriends().add(person2);
        personRepository.save(person1);
    }

    public void unfriend(Long id1, Long id2) {
        Optional<Person> person1 = personRepository.findById(id1);
        Optional<Person> person2 = personRepository.findById(id2);
        if (eitherDoesNotExist(person1, person2)) {
            return;
        }
        unfriend(person1.get(), person2.get());
    }

    private void unfriend(Person person1, Person person2) {
        person1.getFriends().remove(person2);
        personRepository.save(person1);
    }


    public Optional<Person> byName(String name) {
        return personRepository.findOneByName(name);
    }
}
