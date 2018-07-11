package com.beyondcoding.concert;

import org.springframework.stereotype.Component;

@Component("freddy")
public class Singer {

    private final String name;

    public Singer() {
        name = "Freddy Mercury";
    }

    public Singer(String name) {
        this.name = name;
    }

    public void sing() {
        System.out.println("Singer -- (" + name + ") Singing");
    }

}
