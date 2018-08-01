package com.beyondcoding.codingcafe.barista.endpoint;

import com.beyondcoding.codingcafe.barista.api.dto.Beverage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BeverageServiceEndpointTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testGetBeverages() {
        Beverage[] response = restTemplate.getForObject("/orders", Beverage[].class);
        List<Beverage> beverages = Arrays.asList(response);

        assertEquals(3, beverages.size());
        assertContains(beverages, "capuccino");
        assertContains(beverages, "chocolate");
        assertContains(beverages, "macchiato");
    }

    private void assertContains(List<Beverage> beverages, String beverage) {
        boolean exists = beverages.stream()
                .map(Beverage::getName)
                .anyMatch(e -> e.equalsIgnoreCase(beverage));
        assertTrue(beverage + " exists", exists);
    }
}