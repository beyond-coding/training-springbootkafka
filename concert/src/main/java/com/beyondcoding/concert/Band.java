package com.beyondcoding.concert;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class Band {

    private final Singer singer;

    public Band(@Qualifier("freddy") Singer singer) {
        this.singer = singer;
    }

    @PostConstruct
    public void initialize() {
        System.out.println("Band -- (Initialized)");
    }

    @PreDestroy
    public void terminate() {
        System.out.println("Band -- (Terminated)");
    }

    public void play() {
        System.out.println("Band -- Playing music");
        singer.sing();
    }

    public void playWith(Singer anotherSinger) {
        System.out.println("Band -- Playing music");
        anotherSinger.sing();
    }

    public void playFor(Audience audience){
        audience.listenToMusic();
    }

}
