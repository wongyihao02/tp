package studentcommands;

import command.studentcommands.NewStudentCommand;
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

public class NewStudentCommandTest {

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
    public void testAddNewStudentSuccessfully() {
        // Prepare input with valid student details (DOB in dd/MM/yyyy format)
        String input = "John Doe,15/04/2000,Male,91234567,A1234567Y,CS1010S";

        // Execute the command
        Command<TutorialClassList> command = new NewStudentCommand();
        command.execute(input, tutorialClassList);

        // Assert the student has been added to the correct tutorial class
        TutorialClass tutorialClass = tutorialClassList.getTutorialByName("CS1010S");
        Student newStudent = tutorialClass.getStudentList().getStudentByMatricNumber("A1234567Y");

        assertNotNull(newStudent, "New student should be added to the tutorial class.");
        assertEquals("John Doe", newStudent.getName());
        assertEquals("2000-04-15", newStudent.getDateOfBirth().toString()); // Assert the date is parsed correctly
        assertEquals("Male", newStudent.getGender());
        assertEquals("91234567", newStudent.getContact());
        assertEquals("A1234567Y", newStudent.getMatricNumber());

        // Check that the success message was printed
        assertTrue(outputStream.toString().contains("New student added to tutorial class CS1010S: John Doe"));
    }

    @Test
    public void testDuplicateMatricNumber() {
        // Prepare input with a duplicate matric number (DOB in dd/MM/yyyy format)
        String input = "Alice Tan,15/04/2002,Female,91234567,A1234567W,CS1010S";

        // Execute the command
        Command<TutorialClassList> command = new NewStudentCommand();
        command.execute(input, tutorialClassList);

        // Check the output error message for duplicate matric number
        assertTrue(outputStream.toString().contains("Duplicate matric number: " +
                "A student with this matric number already exists."));
    }

    @Test
    public void testInvalidDob() {
        // Prepare input with an invalid date of birth (wrong format)
        String input = "John Doe,invalid-date,Male,91234567,A1234567Y,CS1010S";

        // Execute the command
        Command<TutorialClassList> command = new NewStudentCommand();
        command.execute(input, tutorialClassList);

        // Assert the output error message for invalid DOB
        assertTrue(outputStream.toString().contains("Please enter a valid dob."));
    }

    @Test
    public void testInvalidGender() {
        // Prepare input with invalid gender (DOB in dd/MM/yyyy format)
        String input = "John Doe,15/04/2000,Other,91234567,A1234567Y,CS1010S";

        // Execute the command
        Command<TutorialClassList> command = new NewStudentCommand();
        command.execute(input, tutorialClassList);

        // Assert the output error message for invalid gender
        assertTrue(outputStream.toString().contains("Invalid input: Gender must be 'Male' or 'Female'."));
    }

    @Test
    public void testNonExistingTutorialClass() {
        // Prepare input with a non-existing tutorial class code (DOB in dd/MM/yyyy format)
        String input = "John Doe,15/04/2000,Male,91234567,A1234567Y,CS9999X";

        // Execute the command
        Command<TutorialClassList> command = new NewStudentCommand();
        command.execute(input, tutorialClassList);

        // Assert the output error message for non-existing tutorial class
        assertTrue(outputStream.toString().contains("No tutorial class found with code: CS9999X"));
    }

    @Test
    public void testInvalidMatricNumberFormat() {
        // Prepare input with an invalid matric number format (DOB in dd/MM/yyyy format)
        String input = "John Doe,15/04/2000,Male,91234567,1234567Y,CS1010S";

        // Execute the command
        Command<TutorialClassList> command = new NewStudentCommand();
        command.execute(input, tutorialClassList);

        // Assert the output error message for invalid matric number format
        assertTrue(outputStream.toString().contains("Invalid matric number: Must follow the format A1234567X."));
    }
}
