package com.beyondcoding.data.todos;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TodosEndpointTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private TodoRepository todoRepository;

    @Before
    public void init() {
        todoRepository.deleteAll();
    }


    @Test
    public void testTodoIsCreated() {
        createTodo("Terminar los tests");
    }

    private Todo createTodo(String name) {
        Todo todo = Todo.builder().name(name).build();

        Todo response = restTemplate.postForObject("/todos", todo, Todo.class);
        assertNotNull(response);
        assertNotNull(response.getId());
        assertEquals(name, response.getName());
        assertEquals(false, response.isDone());
        return todo;
    }

    @Test
    public void testTodoIsDone() {
        String name = "Terminar los tests";
        Todo todo = createTodo(name);

        restTemplate.put("/todos/" + name + "/done", todo);

        Todo response = restTemplate.getForObject("/todos/" + name, Todo.class);

        assertNotNull(response);
        assertNotNull(response.getId());
        assertEquals(name, response.getName());
        assertTrue(response.isDone());
    }


}