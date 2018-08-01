package com.beyondcoding.codingcafe.cashier.logic;

import com.beyondcoding.codingcafe.cashier.persistence.domain.Product;
import com.beyondcoding.codingcafe.cashier.persistence.domain.ProductKind;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class ProductLoaderTest {

    @Autowired
    private ProductLoader productLoader;

    @Test
    public void testAsList() {
        List<Product> products = productLoader.asList();
        assertEquals(1, products.size());

        Product product = products.get(0);
        assertEquals("muffin", product.getName());
        assertEquals(ProductKind.FOODSTUFF, product.getKind());
        assertEquals(2, product.getPrice().intValue());
    }
}