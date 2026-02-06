package donk.model.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

class TaskModelTest {

    @Test
    void todoSerialiseAndToString_includeTypeAndStatus() {
        ToDo todo = new ToDo("read book");

        assertEquals("T | 0 | read book", todo.serialiseTask());
        assertEquals("[T][ ] read book", todo.toString());

        todo.markDone();

        assertEquals("T | 1 | read book", todo.serialiseTask());
        assertEquals("[T][X] read book", todo.toString());
    }

    @Test
    void deadlineWithDate_formatsNicely() {
        Deadline deadline = new Deadline("submit", LocalDate.of(2025, 1, 2));

        assertTrue(deadline.toString().contains("Jan 2 2025"));
    }

    @Test
    void deadlineWithRawString_preservesInput() {
        Deadline deadline = new Deadline("submit", "tomorrow");

        assertEquals("D | 0 | submit | tomorrow", deadline.serialiseTask());
        assertEquals("[D][ ] submit (by: tomorrow)", deadline.toString());
    }

    @Test
    void eventSerialiseAndToString_includeTimes() {
        Event event = new Event("meeting", "2pm", "4pm");

        assertEquals("E | 0 | meeting | 2pm | 4pm", event.serialiseTask());
        assertEquals("[E][ ] meeting (from: 2pm to: 4pm)", event.toString());
    }
}
