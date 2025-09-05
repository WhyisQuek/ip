package timmy;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


public class DeadlineTest {
    @Test
    public void toCompleteStringTest() {
        Deadline testDeadline = new Deadline("Test Deadline", "30/8/2025");
        assertEquals("[D][ ] Test Deadline (by: Aug 30 2025)", testDeadline.toCompleteString());
    }

    @Test
    public void toFileStringTest() {
        Deadline testDeadline = new Deadline("Test Deadline", "30/8/2025");
        testDeadline.markAsDone();
        assertEquals("D | 1 | Test Deadline | 30/8/2025", testDeadline.toFileString());
    }
}

