package com.beyondcoding.marathon.runners;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RunnersParser {

    private final ResourceLoader resourceLoader;

    public List<Runner> asList() {
        Resource resource = resourceLoader.getResource("classpath:runners.csv");
        try {
            return Files.lines(resource.getFile().toPath())
                    .skip(1)
                    .map(line -> line.split(";"))
                    .map(columns -> Runner.builder()
                            .name(columns[0])
                            .minutes(Integer.parseInt(columns[1]))
                            .build()
                    )
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

}
