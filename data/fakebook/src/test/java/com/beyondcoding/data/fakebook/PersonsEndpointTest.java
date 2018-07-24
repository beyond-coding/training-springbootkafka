package com.beyondcoding.data.fakebook;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PersonsEndpointTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @SpyBean
    private Persons persons;

    @Test
    void testPersonIsCreated() {
        String name = "Dave";
        Person person = Person.builder()
                .name(name)
                .address(Address.builder().name("Fake Street").build())
                .build();

        Person response = restTemplate.postForObject("/persons", person, Person.class);

        assertAll(
                () -> assertNotNull(response),
                () -> assertEquals(name, response.getName())
        );
    }
}