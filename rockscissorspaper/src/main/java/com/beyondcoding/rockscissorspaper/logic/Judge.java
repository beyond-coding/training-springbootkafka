package com.beyondcoding.rockscissorspaper.logic;

import com.beyondcoding.rockscissorspaper.domain.Result;
import com.beyondcoding.rockscissorspaper.domain.Shape;
import org.springframework.stereotype.Service;

@Service
public class Judge {

    public Result judge(Shape shape1, Shape shape2) {
        return Result.builder()
                .shape1(shape1)
                .shape2(shape2)
                .result(getResult(shape1, shape2))
                .build();
    }

    private String getResult(Shape shape1, Shape shape2) {
        if (shape1.defeats(shape2)) {
            return "Player 1 wins";
        }
        if (shape2.defeats(shape1)) {
            return "Player 2 wins";
        }
        return "Nobody wins";
    }
}
