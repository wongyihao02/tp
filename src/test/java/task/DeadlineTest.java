package task;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import exception.TASyncException;

public class DeadlineTest {

    private Deadline deadlineTask;

    @BeforeEach
    void setUp() throws TASyncException {
        deadlineTask = new Deadline("Have lunch", true, "Monday");
    }

    @Test
    void testConstructor() {
        assertNotNull(deadlineTask);
        assertEquals("Have lunch", deadlineTask.getTaskName());
        assertTrue(deadlineTask.getIsDone());
        assertEquals("Monday", deadlineTask.getDeadline());
        assertEquals(TaskType.DEADLINE, deadlineTask.getTaskType());
    }

    @Test
    void testToFileFormat() {
        String expectedFileFormat = "D,true,Have lunch,Monday\n";
        assertEquals(expectedFileFormat, deadlineTask.toFileFormat());
    }

    @Test
    void testEmptyDeadline() throws TASyncException {
        Deadline emptyDeadlineTask = new Deadline("Read book", false, "");
        assertEquals("", emptyDeadlineTask.getDeadline());
    }
    
}
