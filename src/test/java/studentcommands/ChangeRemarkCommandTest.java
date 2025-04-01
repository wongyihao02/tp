package studentcommands;

import command.studentcommands.ChangeRemarkCommand;
import command.taskcommands.Command;
import studentcommands.utils.StudentCommandTestUtil;
import students.Student;
import tutorial.TutorialClass;
import tutorial.TutorialClassList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ChangeRemarkCommandTest {

    private TutorialClassList tutorialClassList;
    private ByteArrayOutputStream outputStream;

    @BeforeEach
    public void setUp() {
        // Initialize the tutorial class list with predefined classes and students.
        tutorialClassList = StudentCommandTestUtil.initializeTutorialClasses();

        // Capture System.out output
        outputStream = StudentCommandTestUtil.captureSystemOut();
    }

    @Test
    public void testChangeRemarkSuccessfully() {
        // Prepare input for changing a remark (valid input)
        String input = "CS1010S,A1234567W,Excellent performance!";

        // Execute the command
        Command<TutorialClassList> command = new ChangeRemarkCommand();
        command.execute(input, tutorialClassList);

        // Check that the remark has been updated
        TutorialClass tutorialClass = tutorialClassList.getTutorialByName("CS1010S");
        Student student = tutorialClass.getStudentList().getStudentByMatricNumber("A1234567W");

        assertNotNull(student, "Student should be found in the class.");
        assertEquals("Excellent performance!", student.getRemark(), "Remark should be updated successfully.");

        // Check that the success message was printed
        assertTrue(outputStream.toString().contains("Remark updated for student with " +
                "matric number A1234567W in tutorial class CS1010S."));
    }

    @Test
    public void testStudentNotFound() {
        // Prepare input with a non-existing student matric number
        String input = "CS1010S,A1234567Z,Needs improvement";

        // Execute the command
        Command<TutorialClassList> command = new ChangeRemarkCommand();
        command.execute(input, tutorialClassList);

        // Assert the output error message
        assertTrue(outputStream.toString().contains("No student found with matric number: A1234567Z"));
    }

    @Test
    public void testTutorialClassNotFound() {
        // Prepare input with a non-existing tutorial class code
        String input = "CS9999X,A1234567W,Excellent performance!";

        // Execute the command
        Command<TutorialClassList> command = new ChangeRemarkCommand();
        command.execute(input, tutorialClassList);

        // Assert the output error message for tutorial class not found
        assertTrue(outputStream.toString().contains("Invalid change remark command, " +
                "please specify which task to change remark."));
    }

    @Test
    public void testInvalidInputFormat() {
        // Prepare input with invalid format (missing one part)
        String input = "CS1010S,A1234567W"; // Missing the new remark part

        // Execute the command
        Command<TutorialClassList> command = new ChangeRemarkCommand();
        command.execute(input, tutorialClassList);

        // Assert the output error message for invalid input format
        assertTrue(outputStream.toString().contains("Invalid change remark command," +
                " please specify which task to change remark."));
    }

}
