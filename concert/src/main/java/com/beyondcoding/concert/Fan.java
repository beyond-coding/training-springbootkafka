package com.beyondcoding.concert;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Component
@Scope("prototype")
public class Fan implements Audience {

    private final String name;

    public Fan() {
        List<String> names = Arrays.asList("Peter", "Lucy", "James", "Daniela");
        int index = new Random().nextInt(names.size());
        name = names.get(index);
    }

    @PostConstruct
    public void initialize() {
        System.out.println("Fan -- (" + name + ") (Initialized)");
    }

    @PreDestroy
    public void terminate() {
        System.out.println("Fan -- (" + name + ") (Terminated)");
    }

    @Override
    public void listenToMusic() {
        System.out.println("Fan -- (" + name + ") likes the music");
    }
}
