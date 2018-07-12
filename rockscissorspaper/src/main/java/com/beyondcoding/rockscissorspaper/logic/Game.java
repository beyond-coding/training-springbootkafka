package com.beyondcoding.rockscissorspaper.logic;

import com.beyondcoding.rockscissorspaper.domain.Shape;
import com.beyondcoding.rockscissorspaper.io.Output;
import com.beyondcoding.rockscissorspaper.players.Player;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.val;
import lombok.var;
import org.springframework.stereotype.Service;

@Service
@Log
@RequiredArgsConstructor
public class Game {

    private final Player player1;

    private final Player player2;

    private final Judge judge;

    private final Output output;

    public void play() {
        output.line("Welcome to the Rock Scissors Paper game!");

        var playersWantToPlayAgain = true;
        while (playersWantToPlayAgain) {
            playOneRound();
            playersWantToPlayAgain = ask();
        }

        output.line("Goodbye");
    }

    private void playOneRound() {
        Shape shape1 = player1.play();
        Shape shape2 = player2.play();
        val result = judge.judge(shape1, shape2);
        output.display(result);
    }

    private boolean ask() {
        return player1.wantsToPlayAgain() && player2.wantsToPlayAgain();
    }
}
