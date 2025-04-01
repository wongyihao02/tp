package studentcommands;

import command.studentcommands.FindStudentCommand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tutorial.TutorialClassList;
import java.io.ByteArrayOutputStream;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static studentcommands.utils.StudentCommandTestUtil.captureSystemOut;
import static studentcommands.utils.StudentCommandTestUtil.initializeTutorialClasses;

public class FindStudentCommandTest {
    private TutorialClassList tutorialClassList;
    private ByteArrayOutputStream outputStream;

    // Set up method to initialize test data
    @BeforeEach
    void setUp() {
        // Initialize the tutorial class list using the utility method
        tutorialClassList = initializeTutorialClasses();

        // Capture System.out to check command output
        outputStream = captureSystemOut();
    }

    // Test case: Find student by exact name
    @Test
    void testFindStudentByExactName() {
        FindStudentCommand command = new FindStudentCommand();
        command.execute("Alice Tan", tutorialClassList);

        // Capture and check output
        String output = outputStream.toString().trim();
        assertTrue(output.contains("In tutorial CS1010S:"));
        assertTrue(output.contains("Alice Tan"));
        assertTrue(output.contains("A1234567W"));
    }

    // Test case: Find student by partial name (match in multiple tutorials)
    @Test
    void testFindStudentByPartialName() {
        FindStudentCommand command = new FindStudentCommand();
        command.execute("Alice", tutorialClassList);

        // Capture and check output
        String output = outputStream.toString().trim();
        assertTrue(output.contains("In tutorial CS1010S:"));
        assertTrue(output.contains("Alice Tan"));
        assertTrue(output.contains("A1234567W"));

        assertTrue(output.contains("In tutorial CS2040:"));
        assertTrue(output.contains("Alice Lee"));
        assertTrue(output.contains("A1234567Z"));
    }

    // Test case: Find student by matric number
    @Test
    void testFindStudentByMatricNumber() {
        FindStudentCommand command = new FindStudentCommand();
        command.execute("A1234567X", tutorialClassList);

        // Capture and check output
        String output = outputStream.toString().trim();
        assertTrue(output.contains("In tutorial CS1010S:"));
        assertTrue(output.contains("Bob Lim"));
        assertTrue(output.contains("A1234567X"));
    }

    // Test case: No student found by keyword
    @Test
    void testFindStudentNotFound() {
        FindStudentCommand command = new FindStudentCommand();
        command.execute("Charlie", tutorialClassList);

        // Capture and check output
        String output = outputStream.toString().trim();
        assertTrue(output.contains("No students found with the keyword: Charlie"));
    }

    // Test case: Invalid input (empty keyword)
    @Test
    void testFindStudentInvalidInput() {
        FindStudentCommand command = new FindStudentCommand();

        // Testing with empty keyword
        command.execute("", tutorialClassList);
        String output = outputStream.toString().trim();
        assertTrue(output.contains("Invalid find student command"));

        // Testing with null keyword
        command.execute(null, tutorialClassList);
        output = outputStream.toString().trim();
        assertTrue(output.contains("Invalid find student command"));
    }
}
