package com.beyondcoding.marathon.endpoint;

import com.beyondcoding.marathon.domain.Runner;
import com.beyondcoding.marathon.logic.Marathon;
import com.beyondcoding.marathon.logic.RunnersSample;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext
public class RunnersEndpointTest {


    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private RunnersSample sample;

    @SpyBean
    private Marathon marathon;

    @Before
    public void before() {
        marathon.clear();
    }

    @Test
    public void testWithoutRunners() {
        assertNumberOfRunners(0);
        assertNull(restTemplate.getForObject("/runners/winner", Runner.class));
        verify(marathon).getRunners();
        verify(marathon).getWinner();
        verify(marathon, times(1)).add(any(Runner.class));
    }


    @Test
    public void testWithRunners() {
        assertNumberOfRunners(0);

        List<Runner> runners = sample.asList();
        runners.forEach(runner -> restTemplate.postForObject("/runners", runner, Runner.class));

        assertNumberOfRunners(runners.size());
        verify(marathon, times(runners.size())).add(any(Runner.class));

        Runner winner = restTemplate.getForObject("/runners/winner", Runner.class);
        assertNotNull(winner);
        assertEquals("samantha", winner.getName());
        assertEquals(10, winner.getMinutes().intValue());
    }

    private void assertNumberOfRunners(int expected) {
        Runner[] runners = restTemplate.getForObject("/runners", Runner[].class);
        assertEquals(expected, runners.length);
    }
}