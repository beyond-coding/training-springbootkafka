package com.beyondcoding.marathon.logic;

import com.beyondcoding.marathon.domain.Runner;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@ConfigurationProperties("sample")
public class RunnersSample {

    private Map<String, Integer> runners = new HashMap<>();

    public List<Runner> asList() {
        return runners.entrySet().stream()
                .map(entry -> {
                    Runner runner = new Runner();
                    runner.setName(entry.getKey());
                    runner.setMinutes(entry.getValue());
                    return runner;
                })
                .collect(Collectors.toList());
    }

    public Map<String, Integer> getRunners() {
        return runners;
    }
}
