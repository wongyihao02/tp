package studentcommands;

import command.studentcommands.DeleteStudentCommand;
import students.StudentList;
import tutorial.TutorialClassList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static studentcommands.utils.StudentCommandTestUtil.captureSystemOut;
import static studentcommands.utils.StudentCommandTestUtil.initializeTutorialClasses;

public class DeleteStudentCommandTest {

    private TutorialClassList tutorialClassList;
    private ByteArrayOutputStream outputStream;

    @BeforeEach
    void setUp() {
        // Initialize the tutorial class list using the utility method
        tutorialClassList = initializeTutorialClasses();

        // Capture System.out to check command output
        outputStream = captureSystemOut();
    }

    @Test
    void testDeleteStudentSuccessfully() {
        DeleteStudentCommand command = new DeleteStudentCommand();
        command.execute("CS1010S,A1234567W", tutorialClassList);

        String output = outputStream.toString().trim();
        assertTrue(output.contains("Student with matric number A1234567W " +
                "has been removed from tutorial class CS1010S."));

        // Verify that the student is actually removed
        StudentList studentList = tutorialClassList.getTutorialByName("CS1010S").getStudentList();
        assertNull(studentList.getStudentByMatricNumber("A1234567W"));
    }

    @Test
    void testDeleteStudentStudentNotFound() {
        DeleteStudentCommand command = new DeleteStudentCommand();
        command.execute("CS1010S,A1234567Z", tutorialClassList);

        String output = outputStream.toString().trim();
        assertTrue(output.contains("No student found with matric number A1234567Z in tutorial class CS1010S."));
    }

    @Test
    void testDeleteStudentTutorialNotFound() {
        DeleteStudentCommand command = new DeleteStudentCommand();
        command.execute("CS9999,A1234567W", tutorialClassList);

        String output = outputStream.toString().trim();
        assertTrue(output.contains("No tutorial class found with code: CS9999"));
    }

    @Test
    void testDeleteStudentInvalidInput() {
        DeleteStudentCommand command = new DeleteStudentCommand();
        command.execute("InvalidInput", tutorialClassList);

        String output = outputStream.toString().trim();
        assertTrue(output.contains("Invalid delete student command, please specify the tutorial number" +
                " and the matriculation number of the student to delete from the list."));
    }
}
