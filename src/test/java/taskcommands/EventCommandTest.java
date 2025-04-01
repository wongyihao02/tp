package taskcommands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import command.taskcommands.EventCommand;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import task.Event;
import task.TaskList;


class EventCommandTest {
    private TaskList taskList;

    @BeforeEach
    void setUp() {
        taskList = new TaskList();
    }

    @AfterEach
    void tearDown() {
        taskList = null; // Ensure TaskList is reset
    }

    @Test
    void execute_validEventCommand() {
        // Arrange
        EventCommand command = new EventCommand();
        int initialSize = taskList.getTasks().size();
        String validCommand = "Meeting /from 02/04/2025 14:00 /to 02/04/2025 16:00";

        // Act
        command.execute(validCommand, taskList);

        // Assert
        assertEquals(initialSize + 1, taskList.getTasks().size(),
                "Task list size should increase by 1.");
        Event addedEvent = (Event) taskList.getTasks().get(initialSize);
        assertEquals("Meeting", addedEvent.getTaskName());
        assertEquals("02/04/2025 14:00", addedEvent.getEventStart());
        assertEquals("02/04/2025 16:00", addedEvent.getEventEnd());
    }
}
