package com.beyondcoding.marathon.logic;

import com.beyondcoding.marathon.domain.Runner;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class Marathon {

    private List<Runner> runners = new ArrayList<>();

    public List<Runner> getRunners() {
        return runners;
    }

    public void add(Runner runner) {
        runners.add(runner);
    }

    public void delete(String name) {
        runners.stream()
                .filter(e -> e.getName().equalsIgnoreCase(name))
                .findFirst()
                .ifPresent(runners::remove);
    }

    public Optional<Runner> getWinner() {
        return runners.stream()
                .sorted(Comparator.comparing(Runner::getMinutes))
                .findFirst();
    }

    public void clear() {
        runners.clear();
    }
}
