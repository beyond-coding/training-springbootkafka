package com.beyondcoding.concert;

import java.util.List;

public class Crowd implements Audience {

    private final List<Fan> fans;

    public Crowd(List<Fan> fans) {
        this.fans = fans;
    }

    @Override
    public void listenToMusic() {
        fans.stream().forEach(Fan::listenToMusic);
    }
}
