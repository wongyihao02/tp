package task;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TodoTest {

    private Todo todoTask;

    @BeforeEach
    void setUp() {
        todoTask = new Todo("Finish homework", false);
    }

    @Test
    void testConstructor() {
        assertNotNull(todoTask); // Ensure the Todo task object is not null.
        assertEquals("Finish homework", todoTask.getTaskName()); // Verify the task name.
        assertFalse(todoTask.getIsDone()); // Verify the task status (not done).
        assertEquals(TaskType.TODO, todoTask.getTaskType()); // Verify the task type is TODO.
    }

    @Test
    void testToFileFormat() {
        String expectedFileFormat = "T,false,Finish homework,\n";
        assertEquals(expectedFileFormat, todoTask.toFileFormat());
    }
}
