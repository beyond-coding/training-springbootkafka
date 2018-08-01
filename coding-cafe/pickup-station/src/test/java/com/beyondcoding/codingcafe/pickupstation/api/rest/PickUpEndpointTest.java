package com.beyondcoding.codingcafe.pickupstation.api.rest;

import com.beyondcoding.codingcafe.pickupstation.api.dto.Product;
import com.beyondcoding.codingcafe.pickupstation.api.dto.Purchase;
import com.beyondcoding.codingcafe.pickupstation.domain.PurchaseStatus;
import com.beyondcoding.codingcafe.pickupstation.api.event.CustomerNotifier;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PickUpEndpointTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @SpyBean
    private CustomerNotifier customerNotifier;

    @Test
    public void testUnexpectedProductIsReceived() {
        Product product = Product.builder()
                .ticket(0000l)
                .name("unexpected")
                .build();

        PurchaseStatus status = restTemplate.postForObject("/products", product, PurchaseStatus.class);

        assertNull(status);
        verify(customerNotifier, times(0)).notify(any(PurchaseStatus.class));
    }

    @Test
    public void testPurchaseWithOneProduct() {
        long ticket = 1111l;
        String customer = "Thomas";
        String productName = "cookie";

        Purchase purchase = Purchase.builder()
                .ticket(ticket)
                .customer(customer)
                .products(Arrays.asList(productName))
                .build();

        PurchaseStatus status = restTemplate.postForObject("/purchases", purchase, PurchaseStatus.class);

        assertNotNull(status);
        assertEquals(ticket, status.getTicket().intValue());
        assertEquals(false, status.isCompleted());
        assertEquals(customer, status.getCustomer());
        assertEquals(1, status.getRemaining().size());

        Product product = Product.builder()
                .ticket(ticket)
                .name(productName)
                .build();

        status = restTemplate.postForObject("/products", product, PurchaseStatus.class);

        assertNotNull(status);
        assertEquals(ticket, status.getTicket().intValue());
        assertEquals(true, status.isCompleted());
        assertEquals(customer, status.getCustomer());
        assertEquals(0, status.getRemaining().size());
        verify(customerNotifier).notify(any(PurchaseStatus.class));
    }

    @Test
    public void testPurchaseWithManyProducts() {
        long ticket = 2222l;
        String customer = "Lila";
        List<String> productNames = Arrays.asList("cookie", "macchiato", "muffin");
        Purchase purchase = Purchase.builder()
                .ticket(ticket)
                .customer(customer)
                .products(productNames)
                .build();

        PurchaseStatus status = restTemplate.postForObject("/purchases", purchase, PurchaseStatus.class);

        assertEquals(false, status.isCompleted());

        List<Product> products = productNames.stream()
                .map(e -> Product.builder()
                        .ticket(ticket)
                        .name(e)
                        .build()
                )
                .collect(Collectors.toList());

        products.stream()
                .limit(products.size() - 1)
                .forEach(e -> {
                    PurchaseStatus purchaseStatus = restTemplate.postForObject("/products", e, PurchaseStatus.class);
                    assertEquals(false, purchaseStatus.isCompleted());
                    assertFalse("Product removed from list of remaining", purchaseStatus.getRemaining().contains(e.getName()));
                    verify(customerNotifier, times(0)).notify(any(PurchaseStatus.class));
                });

        Product lastProduct = products.get(products.size() - 1);

        status = restTemplate.postForObject("/products", lastProduct, PurchaseStatus.class);

        assertEquals(true, status.isCompleted());
        assertFalse("Product removed from list of remaining", status.getRemaining().contains(lastProduct.getName()));
        assertEquals(0, status.getRemaining().size());
        verify(customerNotifier).notify(any(PurchaseStatus.class));
    }
}