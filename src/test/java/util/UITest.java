package util;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UITest {

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
    public void testPrintWelcome_shouldContainWelcomeMessage() {
        UI ui = new UI();
        ui.printWelcome();
        String output = outContent.toString();
        assertTrue(output.contains("Welcome to TASync!"));
    }

    @Test
    public void testPrintGoodbye_shouldContainGoodbyeMessage() {
        UI ui = new UI();
        ui.printGoodbye();
        String output = outContent.toString();
        assertTrue(output.contains("Goodbye! Have a productive day!"));
    }

    @Test
    public void testGetUserCommand_shouldReturnInput() {
        String simulatedInput = "hello\n";
        ByteArrayInputStream in = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(in);

        UI ui = new UI();
        String result = ui.getUserCommand();

        assertEquals("hello", result);
        ui.close();
    }

    @Test
    public void testPrintDottedLine_shouldPrintSeparator() {
        UI ui = new UI();
        ui.printDottedLine();
        String output = outContent.toString();
        assertTrue(output.contains("----------------------------------------------------"));
    }

    @Test
    public void testPrintLogin_shouldContainLoginPrompt() {
        UI ui = new UI();
        ui.printLogin();
        String output = outContent.toString();
        assertTrue(output.contains("Login menu for TASync"));
    }

    @Test
    public void testPrintCreatePasswordMenu_shouldContainPasswordInstructions() {
        UI ui = new UI();
        ui.printCreatePasswordMenu();
        String output = outContent.toString();
        assertTrue(output.contains("Password needs to be at least 8 characters long"));
    }

    @Test
    public void testPrintMessage_shouldPrintCustomMessage() {
        UI ui = new UI();
        ui.printMessage("Test Message");
        String output = outContent.toString();
        assertTrue(output.contains("Test Message"));
    }
}
