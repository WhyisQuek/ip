package Timmy;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ToDoTest {
    @Test
    public void toCompleteStringTest() {
        ToDo testToDo = new ToDo("Test ToDo");
        testToDo.markAsDone();
        assertEquals("[T][X] Test ToDo", testToDo.toCompleteString());
    }

    @Test
    public void toFileStringTest() {
        ToDo testToDo = new ToDo("Test ToDo");
        testToDo.markAsDone();
        assertEquals("T | 1 | Test ToDo", testToDo.toFileString());
    }
}

