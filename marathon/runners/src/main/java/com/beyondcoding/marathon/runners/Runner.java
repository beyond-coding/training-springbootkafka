package com.beyondcoding.marathon.runners;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Runner {

    private final String name;

    private final Integer minutes;

}
