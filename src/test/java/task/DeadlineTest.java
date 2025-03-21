package task;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import exception.TASyncException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DeadlineTest {

    private Deadline deadlineTask;

    @BeforeEach
    void setUp() throws TASyncException {
        deadlineTask = new Deadline("Have lunch", true, "20/12/2025 11:12");
    }

    @Test
    void testConstructor() {
        assertNotNull(deadlineTask);
        assertEquals("Have lunch", deadlineTask.getTaskName());
        assertTrue(deadlineTask.getIsDone());
        assertEquals("20/12/2025 11:12", deadlineTask.getDeadline());
        assertEquals(TaskType.DEADLINE, deadlineTask.getTaskType());
    }

    @Test
    void testToFileFormat() {
        String expectedFileFormat = "D,true,Have lunch,20/12/2025 11:12\n";
        assertEquals(expectedFileFormat, deadlineTask.toFileFormat());
    }

}
