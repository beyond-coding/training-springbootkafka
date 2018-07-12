package com.beyondcoding.rockscissorspaper.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Shape {

    private final String name;

    private final String defeats;

    public boolean defeats(Shape other) {
        return defeats.contains(other.getName());
    }

}
