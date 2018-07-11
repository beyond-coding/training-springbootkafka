package com.beyondcoding.rockscissorspaper.players;

import com.beyondcoding.rockscissorspaper.domain.Shape;
import com.beyondcoding.rockscissorspaper.logic.Shapes;
import lombok.RequiredArgsConstructor;

import java.util.Random;

@RequiredArgsConstructor
public class ComputerPlayer implements Player {

    private final Shapes shapes;

    private Random random = new Random();

    @Override
    public Shape play() {
        int numberOfShapes = shapes.asList().size();
        int choice = random.nextInt(numberOfShapes);
        return shapes.asList().get(choice);
    }

    @Override
    public boolean wantsToPlayAgain() {
        return true;
    }
}
