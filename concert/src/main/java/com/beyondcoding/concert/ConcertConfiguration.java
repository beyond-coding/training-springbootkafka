package com.beyondcoding.concert;

import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import java.util.stream.IntStream;

@Configuration
public class ConcertConfiguration {

    @Bean
    @Order(2)
    ApplicationRunner rehearse(Band band, ApplicationContext applicationContext) {
        return args -> {
            System.out.println("-- REHEARSAL STARTS");

            IntStream.generate(() -> 1)
                    .limit(3)
                    .mapToObj(e -> applicationContext.getBean(Fan.class))
                    .forEach(fan -> band.playFor(fan));


            Fan fan = applicationContext.getBean(Fan.class);
            System.out.println("-- REHEARSAL ENDS\n\n");
        };
    }

    @Bean
    @Order(3)
    ApplicationRunner concert(Band band) {
        return args -> {
            System.out.println("-- CONCERT STARTS");


            System.out.println("-- CONCERT ENDS\n\n");
        };
    }

}
