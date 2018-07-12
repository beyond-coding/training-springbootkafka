package com.beyondcoding.rockscissorspaper.logic;

import com.beyondcoding.rockscissorspaper.domain.Result;
import com.beyondcoding.rockscissorspaper.domain.Shape;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class JudgeTest {

    private Judge judge = new Judge();

    private Shape shape1;

    private Shape shape2;

    @Before
    public void before() {
        shape1 = Mockito.mock(Shape.class);
        shape2 = Mockito.mock(Shape.class);
    }

    @Test
    public void player1wins() {
        when(shape1.defeats(any(Shape.class)))
                .thenReturn(true);

        Result result = judge.judge(shape1, shape2);

        assertEquals("Player 1 wins", result.getResult());
    }

    @Test
    public void player2wins() {
        when(shape2.defeats(any(Shape.class)))
                .thenReturn(true);

        Result result = judge.judge(shape1, shape2);

        assertEquals("Player 2 wins", result.getResult());
    }

    @Test
    public void nobodywins() {
        Result result = judge.judge(shape1, shape2);

        assertEquals("Nobody wins", result.getResult());
    }
}