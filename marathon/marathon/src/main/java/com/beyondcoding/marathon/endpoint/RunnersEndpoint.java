package com.beyondcoding.marathon.endpoint;

import com.beyondcoding.marathon.domain.Runner;
import com.beyondcoding.marathon.logic.Marathon;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/runners")
public class RunnersEndpoint {

    private final Marathon marathon;

    public RunnersEndpoint(Marathon marathon) {
        this.marathon = marathon;
    }

    @GetMapping
    List<Runner> all() {
        return marathon.getRunners();
    }

    @PostMapping
    Runner add(@RequestBody Runner runner) {
        marathon.add(runner);
        return runner;
    }

    @DeleteMapping("/{name}")
    void delete(@PathVariable String name) {
        marathon.delete(name);
    }

    @GetMapping("/winner")
    Runner winner() {
        Optional<Runner> winner = marathon.getWinner();
        if (!winner.isPresent()) {
            return null;
        }
        return winner.get();
    }

}
