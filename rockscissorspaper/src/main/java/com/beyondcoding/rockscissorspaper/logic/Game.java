package com.beyondcoding.rockscissorspaper.logic;

import com.beyondcoding.rockscissorspaper.domain.Shape;
import com.beyondcoding.rockscissorspaper.players.Player;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.val;
import lombok.var;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log
@RequiredArgsConstructor
public class Game {

    private final Judge judge;

    private final List<Player> players;

    public void play() {
        log.info("Welcome to the Rock Scissors Paper game!");

        var playersWantToPlayAgain = true;
        while (playersWantToPlayAgain) {
            playOneRound();
            playersWantToPlayAgain = ask();
        }

        log.info("Goodbye");
    }

    private void playOneRound() {
        Player player1 = players.get(0);
        Player player2 = players.get(1);
        Shape shape1 = player1.play();
        Shape shape2 = player2.play();
        val result = judge.judge(shape1, shape2);
        System.out.println("Player 1 played " + result.getShape1().getName());
        System.out.println("Player 2 played " + result.getShape2().getName());
        System.out.println(result.getResult());
    }

    private boolean ask() {
        return players.stream()
                .allMatch(player -> player.wantsToPlayAgain() == true);
    }
}
