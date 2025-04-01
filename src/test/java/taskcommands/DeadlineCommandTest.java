package taskcommands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import command.taskcommands.DeadlineCommand;
import exception.TASyncException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import task.Deadline;
import task.TaskList;


class DeadlineCommandTest {
    private TaskList taskList;

    @BeforeEach
    void setUp() {
        taskList = new TaskList(new ArrayList<>());
    }
    @AfterEach
    void tearDown() {
        taskList = null; // Ensure TaskList is reset
    }

    @Test
    void execute_validInput() throws TASyncException {
        // Arrange
        DeadlineCommand command = new DeadlineCommand();
        String input = "Submit Report /by 02/04/2025 18:00";

        // Act
        command.execute(input, taskList);

        Deadline deadline = (Deadline) taskList.getTasks().get(0);
        assertEquals("Submit Report", deadline.getTaskName());
        assertEquals("02/04/2025 18:00", deadline.getDeadline());
    }
}
