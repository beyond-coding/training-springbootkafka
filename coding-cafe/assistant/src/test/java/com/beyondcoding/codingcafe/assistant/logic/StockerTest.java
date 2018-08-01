package com.beyondcoding.codingcafe.assistant.logic;

import com.beyondcoding.codingcafe.assistant.persistence.domain.Foodstuff;
import com.beyondcoding.codingcafe.assistant.persistence.repository.FoodstuffRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class StockerTest {

    @Autowired
    private Stocker stocker;

    @Autowired
    private FoodstuffRepository foodstuffRepository;

    @Test
    public void testNoRestockIsNeeded() {
        int expectedNumberOfFoodstuff = 9;
        long actualNumberOfProducts = foodstuffRepository.count();
        assertEquals(expectedNumberOfFoodstuff, actualNumberOfProducts);

        stocker.restock();

        actualNumberOfProducts = foodstuffRepository.count();
        assertEquals(expectedNumberOfFoodstuff, actualNumberOfProducts);
    }

    @Test
    public void testRestockIsNeeded() {
        List<String> products = Arrays.asList("muffin", "cheese-cake", "cookie");
        int expectedNumberOfEachProduct = 3;
        assertStock(products, expectedNumberOfEachProduct);

        IntStream.range(0, products.size())
                .forEach(number -> delete(number, products));

        assertEquals(2, foodstuffRepository.countByName("muffin"));
        assertEquals(1, foodstuffRepository.countByName("cheese-cake"));
        assertEquals(0, foodstuffRepository.countByName("cookie"));

        stocker.restock();

        assertStock(products, expectedNumberOfEachProduct);
    }

    private void assertStock(List<String> products, int expectedNumberOfEachProduct) {
        products.stream()
                .map(foodstuffRepository::countByName)
                .forEach(e -> assertEquals(expectedNumberOfEachProduct, e.intValue()));
    }

    private void delete(int number, List<String> products) {
        String productName = products.get(number);
        List<Foodstuff> stock = foodstuffRepository.findByName(productName);
        stock.stream()
                .limit(number + 1)
                .map(Foodstuff::getId)
                .forEach(id -> foodstuffRepository.deleteById(id));
    }


}