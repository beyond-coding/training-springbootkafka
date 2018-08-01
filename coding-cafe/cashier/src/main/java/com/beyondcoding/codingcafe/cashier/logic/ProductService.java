package com.beyondcoding.codingcafe.cashier.logic;

import com.beyondcoding.codingcafe.cashier.persistence.domain.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductLoader productLoader;

    private List<Product> products;

    @PostConstruct
    private void init() {
        products = productLoader.asList();
    }

    public List<Product> from(List<String> productNames) {
        return productNames.stream()
                .map(this::toProduct)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }

    private Optional<Product> toProduct(String productName) {
        return products.stream()
                .filter(e -> e.getName().equalsIgnoreCase(productName))
                .map(this::copy)
                .findFirst();
    }

    private Product copy(Product product) {
        Product copy = new Product();
        BeanUtils.copyProperties(product, copy);
        return copy;
    }
}
