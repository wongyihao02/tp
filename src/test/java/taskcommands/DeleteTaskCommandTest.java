package taskcommands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import command.taskcommands.DeleteTaskCommand;
import exception.TASyncException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import task.Deadline;
import task.TaskList;


class DeleteTaskCommandTest {
    private TaskList taskList;

    @BeforeEach
    void setUp() throws TASyncException {
        taskList = new TaskList();
        taskList.addTask(new Deadline("Submit Report", false, "02/04/2025 18:00"));
        taskList.addTask(new Deadline("Prepare Slides", false, "03/04/2025 14:00"));
    }

    @AfterEach
    void tearDown() {
        taskList = null; // Ensure TaskList is reset
    }

    @Test
    void execute_validTaskNumber() {
        // Arrange
        DeleteTaskCommand command = new DeleteTaskCommand();
        int initialSize = taskList.getTasks().size();

        // Act
        command.execute("1", taskList);

        // Assert
        assertEquals(initialSize - 1, taskList.getTasks().size(),
                "Task list size should decrease by 1.");
    }
}
