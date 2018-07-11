package com.beyondcoding.concert;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

@Component
//@Profile("casting")
@Order(1)
public class Casting implements ApplicationRunner {

    private final Band band;

    private final ApplicationContext applicationContext;

    public Casting(Band band, ApplicationContext applicationContext) {
        this.band = band;
        this.applicationContext = applicationContext;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("-- CASTING STARTS");

        band.play();

        String[] singerNames = applicationContext.getBeanNamesForType(Singer.class);

        System.out.println("-- The following singers are up for the challenge: ");
        Stream.of(singerNames)
                .forEach(System.out::println);

        System.out.println("-- Setting up the level with Freddy");
        Singer freddy = applicationContext.getBean("freddy", Singer.class);
        band.playWith(freddy);

        System.out.println("-- And now one round with each of the singers");
        Stream.of(singerNames)
                .map(singerName -> applicationContext.getBean(singerName, Singer.class))
                .forEach(singer -> band.playWith(singer));

        System.out.println("-- CASTING ENDS\n\n");
    }
}
