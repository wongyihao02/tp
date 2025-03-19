package taskCommands;

import static org.junit.jupiter.api.Assertions.*;

import Command.taskCommands.EventCommand;
import org.junit.jupiter.api.Test;

import task.Task;
import task.TaskList;
import exception.TASyncException;
import task.Event;

import java.util.ArrayList;

class EventCommandTest {

    @Test
    void testValidEventInput() {
        // Create a mock TaskList object (this can be a real object once your teammates finish their implementation)
        TaskList taskList = new TaskList();

        // Create a valid event string input
        String validEventCommand = "Meeting /from 15/03/2025 14:30 /to 15/03/2025 16:00";

        // Create the EventCommand instance
        EventCommand eventCommand = new EventCommand();

        // Simulate executing the event command
        eventCommand.execute(validEventCommand, taskList);

        // Check that the event has been added to the task list (taskList.addTask() should work properly)
        ArrayList<Task> tasks = taskList.getTasks(); // Assuming you have a getTasks() method
        assertEquals(1, tasks.size(), "Task list should contain one event task.");

        Event event = (Event) tasks.get(0);
        assertEquals("Meeting", event.getTaskName(), "The event name should be 'Meeting'");
        //assertEquals("15/03/2025 14:30", event.getEventStart(), "The event start time should be '15/03/2025 14:30'");
        //assertEquals("15/03/2025 16:00", event.getEventEnd(), "The event end time should be '15/03/2025 16:00'");
    }

    @Test
    void testInvalidEventInput() {
        // Create a mock TaskList object
        TaskList taskList = new TaskList();

        // Create an invalid event string input (invalid date format)
        String invalidEventCommand = "Meeting /from 2025-03-15 14:30 /to 2025-03-15 16:00";

        // Create the EventCommand instance
        EventCommand eventCommand = new EventCommand();

        // Capture the output or check the state of taskList
        eventCommand.execute(invalidEventCommand, taskList);

        // Assert that no event task is added (because of invalid date format)
        assertEquals(0, taskList.getTasks().size(), "Task list should contain no event task due to invalid input.");
    }

    @Test
    void testInvalidEventInputFormat() {
        // Create a mock TaskList object
        TaskList taskList = new TaskList();

        // Create an event string input without /from and /to
        String invalidFormatCommand = "Meeting without time information";

        // Create the EventCommand instance
        EventCommand eventCommand = new EventCommand();

        // Expect exception for invalid format
        TASyncException thrown = assertThrows(TASyncException.class, () -> {
            eventCommand.execute(invalidFormatCommand, taskList);
        });

        assertEquals("Invalid event command, please specify duration of event with \"/from\" and \"/to\".", thrown.getMessage());
    }
}
