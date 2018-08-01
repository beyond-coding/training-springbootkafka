package com.beyondcoding.codingcafe.assistant.domain;

import com.beyondcoding.codingcafe.assistant.persistence.domain.Foodstuff;
import com.beyondcoding.codingcafe.assistant.persistence.repository.FoodstuffRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class FoodstuffRepositoryTest {

    @Autowired
    private FoodstuffRepository foodstuffRepository;

    @Test
    public void findAll() {
        List<Foodstuff> foodstuffs = foodstuffRepository.findAll();

        assertEquals(9, foodstuffs.size());
        assertExists(foodstuffs, "muffin");
        assertExists(foodstuffs, "cheese-cake");
        assertExists(foodstuffs, "cookie");
    }

    private void assertExists(List<Foodstuff> foodstuffs, String product) {
        boolean exists = foodstuffs.stream()
                .anyMatch(e -> e.getName().equals(product));
        assertTrue("Foodstuff exists", exists);
    }


}