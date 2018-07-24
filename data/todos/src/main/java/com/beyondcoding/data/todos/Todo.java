package com.beyondcoding.data.todos;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document
public class Todo {

    @Id
    private String id;

    private String name;

    private boolean done;

}
