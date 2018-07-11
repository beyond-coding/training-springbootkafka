package com.beyondcoding.concert;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SingerConfiguration {

    @Bean
    Singer rob() {
        return new Singer("Rob Swire");
    }

    @Bean
    Singer david() {
        return new Singer("David Bowie");
    }

}
