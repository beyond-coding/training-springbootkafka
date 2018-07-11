package com.beyondcoding.rockscissorspaper.players;

import com.beyondcoding.rockscissorspaper.domain.Shape;
import com.beyondcoding.rockscissorspaper.logic.Shapes;
import lombok.RequiredArgsConstructor;

import java.util.Optional;
import java.util.Scanner;


@RequiredArgsConstructor
public class HumanPlayer implements Player {

    private final Shapes shapes;

    private Scanner scanner = new Scanner(System.in);

    @Override
    public Shape play() {
        Optional<Shape> shape = pickOneShape();
        if (!shape.isPresent()) {
            return play();
        }
        return shape.get();
    }

    private Optional<Shape> pickOneShape() {
        System.out.print("Please, choose one shape (" + shapes.asString() + "): ");
        String input = scanner.next();
        return shapes.asList().stream()
                .filter(shape -> shape.getName().equalsIgnoreCase(input))
                .findFirst();
    }

    @Override
    public boolean wantsToPlayAgain() {
        System.out.print("Do you want to play again? (y/n): ");
        String input = scanner.next();
        return "y".equalsIgnoreCase(input);
    }
}
