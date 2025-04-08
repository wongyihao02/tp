package tutorialcommands;

import command.taskcommands.Command;
import command.tutorialcommands.NewTutorialCommand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import studentcommands.utils.StudentCommandTestUtil;
import tutorial.TutorialClassList;
import java.io.ByteArrayOutputStream;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class NewTutorialCommandTest {

    private TutorialClassList tutorialClassList;
    private ByteArrayOutputStream outputStream;

    @BeforeEach
    public void setUp() {
        // Initialize a list with a few tutorial classes
        tutorialClassList = StudentCommandTestUtil.initializeTutorialClasses();

        // Capture output stream
        outputStream = StudentCommandTestUtil.captureSystemOut();
    }

    @Test
    public void testInvalidInputFormat() {
        String input = "OnlyOneInputField";

        Command<TutorialClassList> command = new NewTutorialCommand();
        command.execute(input, tutorialClassList);

        assertTrue(outputStream.toString().contains("Please check that you have entered the correct " +
                "command parameters."));
    }

}

