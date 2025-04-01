package taskcommands;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import command.taskcommands.ListTaskCommand;
import task.TaskList;
import task.Todo;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class ListTaskCommandTest {
    private TaskList taskList;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        taskList = new TaskList(new ArrayList<>());
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void tearDown() {
        taskList = null; // Ensure TaskList is reset
    }

    @Test
    void execute_withTasks() {
        // Arrange: Add a task to the task list
        taskList.addTask(new Todo("Study Java", false));

        // Act: Create a new ListTaskCommand and execute it
        ListTaskCommand command = new ListTaskCommand();
        command.execute("", taskList);

        // Assert: Verify that the task is printed
        assertTrue(outContent.toString().contains("Study Java"), "The task should be printed in the output.");
    }
}
