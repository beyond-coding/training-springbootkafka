package com.beyondcoding.rockscissorspaper.players;

import com.beyondcoding.rockscissorspaper.domain.Shape;
import com.beyondcoding.rockscissorspaper.io.Input;
import com.beyondcoding.rockscissorspaper.io.Output;
import com.beyondcoding.rockscissorspaper.logic.Shapes;
import lombok.RequiredArgsConstructor;
import lombok.val;

import java.util.Optional;


@RequiredArgsConstructor
public class HumanPlayer implements Player {

    private final Shapes shapes;

    private final Input input;

    private final Output output;

    @Override
    public Shape play() {
        val shape = pickOneShape();
        if (!shape.isPresent()) {
            output.line("Sorry, couldn't recognize your writting");
            return play();
        }
        return shape.get();
    }

    private Optional<Shape> pickOneShape() {
        output.text("Please, choose one shape (" + shapes.asString() + "): ");
        val choice = input.get();
        return shapes.asList().stream()
                .filter(shape -> shape.getName().equalsIgnoreCase(choice))
                .findFirst();
    }

    @Override
    public boolean wantsToPlayAgain() {
        output.text("Do you want to play again? (y/n): ");
        val choice = input.get();
        return "y".equalsIgnoreCase(choice);
    }
}
