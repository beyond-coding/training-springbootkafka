package com.beyondcoding.rockscissorspaper.players;

import com.beyondcoding.rockscissorspaper.domain.Shape;

public interface Player {

    Shape play();

    boolean wantsToPlayAgain();

}
