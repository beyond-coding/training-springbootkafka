package com.beyondcoding.marathonui.logic;

import com.beyondcoding.marathonui.domain.Runner;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
@ConfigurationProperties("marathon.server")
public class Marathon {

    private final RestTemplate restTemplate;

    private String runnersUrl;

    private String winnerUrl;

    public Marathon(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    public Runner getWinner() {
        return restTemplate.getForObject(winnerUrl, Runner.class);
    }

    public List<Runner> getRunners() {
        Runner[] runners = restTemplate.getForObject(runnersUrl, Runner[].class);
        return Arrays.asList(runners);
    }

    public void add(Runner runner) {
        restTemplate.postForObject(runnersUrl, runner, Runner.class);
    }

    public void setRunnersUrl(String runnersUrl) {
        this.runnersUrl = runnersUrl;
    }

    public void setWinnerUrl(String winnerUrl) {
        this.winnerUrl = winnerUrl;
    }


}
