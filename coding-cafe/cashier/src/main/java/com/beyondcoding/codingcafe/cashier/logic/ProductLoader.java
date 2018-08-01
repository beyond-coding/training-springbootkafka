package com.beyondcoding.codingcafe.cashier.logic;

import com.beyondcoding.codingcafe.cashier.persistence.domain.Product;
import com.beyondcoding.codingcafe.cashier.persistence.domain.ProductKind;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductLoader {

    private final ResourceLoader resourceLoader;

    public List<Product> asList() {
        try {
            Resource resource = resourceLoader.getResource("classpath:products.csv");
            Path path = Paths.get(resource.getURI());
            return Files.lines(path)
                    .skip(1)
                    .map(line -> line.split(";"))
                    .map(columns -> toProduct(columns))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private Product toProduct(String[] columns) {
        return Product.builder()
                .name(columns[0])
                .kind(ProductKind.valueOf(columns[1].toUpperCase()))
                .price(Integer.parseInt(columns[2]))
                .build();
    }

}
