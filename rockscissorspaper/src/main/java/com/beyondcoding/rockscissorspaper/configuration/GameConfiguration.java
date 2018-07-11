package com.beyondcoding.rockscissorspaper.configuration;

import com.beyondcoding.rockscissorspaper.domain.Shape;
import com.beyondcoding.rockscissorspaper.logic.Game;
import com.beyondcoding.rockscissorspaper.logic.Shapes;
import com.beyondcoding.rockscissorspaper.players.ComputerPlayer;
import com.beyondcoding.rockscissorspaper.players.HumanPlayer;
import com.beyondcoding.rockscissorspaper.players.Player;
import lombok.Setter;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Configuration
@ConfigurationProperties("game")
public class GameConfiguration {

    @Setter
    private Map<String, String> availableShapes;

    @Bean
    List<Shape> allShapes() {
        return availableShapes.entrySet().stream()
                .map(e -> Shape.builder()
                        .name(e.getKey())
                        .defeats(e.getValue())
                        .build())
                .collect(Collectors.toList());
    }

    @Bean
    List<Player> players(Shapes shapes) {
        return Arrays.asList(
                new HumanPlayer(shapes),
                new ComputerPlayer(shapes)
        );
    }

    @Bean
    ApplicationRunner play(Game game) {
        return args -> game.play();
    }

}
