package util;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CommandListPrinterTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void testPrintCommands_shouldPrintDescriptionsForAllCommands() {
        CommandListPrinter.printCommands();
        String output = outContent.toString();

        // Check that the header is printed
        assertTrue(output.contains("Valid Commands"));

        // Spot-check a few known commands and usages
        assertTrue(output.contains("MARK: Marks a task as done"));
        assertTrue(output.contains("TODO: Creates a new todo task"));
        assertTrue(output.contains("COMMENT: Adds a comment to a student"));
        assertTrue(output.contains("VIEWCOMMENT: Shows comments on a student"));
        assertTrue(output.contains("CREATE: Creates an attendance list for the given week"));
    }
}
