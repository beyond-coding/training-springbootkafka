package com.beyondcoding.codingcafe.assistant.persistence.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class Foodstuff {

    @Id
    private String id;

    private String name;

    public Foodstuff() {
    }

    public Foodstuff(String name) {
        this.name = name;
    }

}
