package com.beyondcoding.marathon.runners;

import lombok.Setter;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@ConfigurationProperties("runners")
public class RunnersConfiguration {

    @Setter
    private String url;

    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    ApplicationRunner sender(RunnersParser runners, RestTemplate restTemplate) {
        return args -> {
            runners.asList()
                    .forEach(runner -> restTemplate.postForObject(url, runner, Runner.class));
        };
    }

}
