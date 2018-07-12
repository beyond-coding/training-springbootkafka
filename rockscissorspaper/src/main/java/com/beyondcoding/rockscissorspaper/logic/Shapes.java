package com.beyondcoding.rockscissorspaper.logic;

import com.beyondcoding.rockscissorspaper.domain.Shape;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class Shapes {

    private final List<Shape> shapes;

    public List<Shape> asList() {
        return new ArrayList<>(shapes);
    }

    public String asString() {
        return shapes.stream()
                .map(Shape::getName)
                .collect(Collectors.joining(", "));
    }


}
