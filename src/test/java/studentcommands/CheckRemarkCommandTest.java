package studentcommands;

import command.studentcommands.CheckRemarkCommand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import studentcommands.utils.StudentCommandTestUtil;
import tutorial.TutorialClassList;

import java.io.ByteArrayOutputStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CheckRemarkCommandTest {

    private TutorialClassList tutorialClassList;
    private ByteArrayOutputStream outputStream;

    @BeforeEach
    void setUp() {
        tutorialClassList = StudentCommandTestUtil.initializeTutorialClasses();
        outputStream = StudentCommandTestUtil.captureSystemOut();
    }

    @Test
    void testCheckRemarkStudentHasUpdatedRemark() {
        // Retrieve the student and update their remark
        tutorialClassList.getTutorialByName("CS1010S")
                .getStudentList()
                .getStudentByMatricNumber("A1234567W")
                .setRemark("Great improvement in participation!");

        CheckRemarkCommand command = new CheckRemarkCommand();
        command.execute("CS1010S,A1234567W", tutorialClassList);

        String output = outputStream.toString().trim();
        assertTrue(output.contains("Remarks for Alice Tan: Great improvement in participation!"));
    }


    @Test
    void testCheckRemarkStudentNoRemark() {
        CheckRemarkCommand command = new CheckRemarkCommand();
        command.execute("CS1010S,A1234567X", tutorialClassList);

        String output = outputStream.toString().trim();
        assertTrue(output.contains("No remarks found for student: Bob Lim"));
    }

    @Test
    void testCheckRemarkStudentNotFound() {
        CheckRemarkCommand command = new CheckRemarkCommand();
        command.execute("CS1010S,A9999999Z", tutorialClassList);

        String output = outputStream.toString().trim();
        assertTrue(output.contains("Invalid check remark command, please specify which a valid student " +
                "and tutorial to check."));
    }

    @Test
    void testCheckRemarkTutorialNotFound() {
        CheckRemarkCommand command = new CheckRemarkCommand();
        command.execute("CS9999,A1234567W", tutorialClassList);

        String output = outputStream.toString().trim();
        assertTrue(output.contains("Invalid check remark command, please specify which a valid student " +
                "and tutorial to check."));
    }

    @Test
    void testCheckRemarkInvalidInput() {
        CheckRemarkCommand command = new CheckRemarkCommand();
        command.execute("InvalidInput", tutorialClassList);

        String output = outputStream.toString().trim();
        assertTrue(output.contains("Please check that you have entered the correct command parameters."));
    }
}
