package com.beyondcoding.marathon.logic;

import com.beyondcoding.marathon.domain.Runner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class MarathonTest {

    @Autowired
    private Marathon marathon;

    @Autowired
    private RunnersSample sample;


    @Before
    public void before() {
        sample.asList().stream()
                .forEach(marathon::add);
    }

    @After
    public void after() {
        sample.asList().stream()
                .map(Runner::getName)
                .forEach(marathon::delete);
    }

    @Test
    public void testNumberOfRunners() {
        assertEquals(6, marathon.getRunners().size());
    }

    @Test
    public void testWinner() {
        Optional<Runner> winner = marathon.getWinner();

        assertTrue(winner.isPresent());
        assertEquals("samantha", winner.get().getName());
        assertEquals(10, winner.get().getMinutes().intValue());
    }
}