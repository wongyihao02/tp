package taskcommands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import command.taskcommands.FindTaskCommand;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import task.TaskList;
import task.Todo;


class FindTaskCommandTest {
    private TaskList taskList;
    private FindTaskCommand findCommand;

    @BeforeEach
    void setUp() {
        taskList = new TaskList();
        findCommand = new FindTaskCommand();

        // Add some tasks to the task list
        taskList.addTask(new Todo("Complete homework", false));
        taskList.addTask(new Todo("Attend meeting", false));
        taskList.addTask(new Todo("Complete project", false));
    }

    @AfterEach
    void tearDown() {
        taskList = null; // Ensure TaskList is reset
    }

    @Test
    void execute_validFindCommand() {
        // Arrange
        String keyword = "Complete";

        // Act
        findCommand.execute(keyword, taskList);

        // Assert
        assertEquals(2, taskList.getTaskListWithKeyWord(keyword).getTaskCount(),
                "Two tasks should contain the keyword 'Complete'");
    }
}
