package taskcommands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import command.taskcommands.ByeCommand;
import task.TaskList;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

class ByeCommandTest {

    @Test
    void testExecute() {
        // Arrange
        ByeCommand byeCommand = new ByeCommand();
        TaskList taskList = new TaskList(); // Assuming TaskList has a default constructor
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        // Act
        byeCommand.execute("", taskList);
        System.setOut(originalOut); // Reset standard output

        // Assert
        assertEquals("Bye. Hope to see you again soon!" + System.lineSeparator(), outputStream.toString());
    }
}
