package com.beyondcoding.reactive.hellothere;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.SynchronousSink;
import reactor.util.function.Tuple2;

import java.time.Duration;

@RestController
@RequestMapping("/")
public class HelloThereEndpoint {

    @GetMapping
    Mono<String> helloThere() {
        return Mono.just("Hello there!");
    }

    @GetMapping(value = "/{name}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    Flux<String> helloThereName(@PathVariable String name) {
        return Flux.interval(Duration.ofSeconds(2))
                .map(tick -> "Hello there, " + name);
    }

    @GetMapping(value = "/formal/{name}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    Flux<String> helloThereNameFormal(@PathVariable String name) {
        Flux<Long> interval = Flux.interval(Duration.ofSeconds(2));
        Flux<String> names = Flux.generate((SynchronousSink<String> sink) -> sink.next("Hello there, Mr. " + name));

        return Flux.zip(interval, names)
                .map(Tuple2::getT2);
    }

}
