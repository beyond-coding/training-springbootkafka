package com.beyondcoding.rockscissorspaper.io;

import com.beyondcoding.rockscissorspaper.domain.Result;
import org.springframework.stereotype.Service;

@Service
public class Output {

    public void text(String message) {
        System.out.print(message);
    }

    public void line(String message) {
        System.out.println(message);
    }

    public void display(Result result) {
        line("Player 1 played " + result.getShape1().getName());
        line("Player 2 played " + result.getShape2().getName());
        line(result.getResult());
    }
}
