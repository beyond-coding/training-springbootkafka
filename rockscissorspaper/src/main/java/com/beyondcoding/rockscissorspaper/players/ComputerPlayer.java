package com.beyondcoding.rockscissorspaper.players;

import com.beyondcoding.rockscissorspaper.domain.Shape;
import com.beyondcoding.rockscissorspaper.logic.Shapes;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Random;

@RequiredArgsConstructor
public class ComputerPlayer implements Player {

    private final Shapes shapes;

    private Random random = new Random();

    @Override
    public Shape play() {
        return chooseOneShapeAtRandom();
    }

    private Shape chooseOneShapeAtRandom() {
        List<Shape> choices = shapes.asList();
        int choice = random.nextInt(choices.size());
        return choices.get(choice);
    }

    @Override
    public boolean wantsToPlayAgain() {
        return true;
    }
}
