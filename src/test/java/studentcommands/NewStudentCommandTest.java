package studentcommands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static studentcommands.FindStudentCommandTest.createTutorial;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalTime;
import tutorial.TutorialClass;
import tutorial.TutorialClassList;
import students.Student;
import command.studentcommands.NewStudentCommand;

public class NewStudentCommandTest {

    private TutorialClassList tutorialClassList;

    @BeforeEach
    public void setup() {
        tutorialClassList = new TutorialClassList();
        TutorialClass tutorialClass = createTutorial("T11", 2, LocalTime.of(10, 0), LocalTime.of(12, 0));
        tutorialClassList.addTutorialClass(tutorialClass);
    }

    @Test
    public void testValidNewStudent() {
        String input = "Mark,20/10/1987,Male,12345678,A2457654W,T11";
        NewStudentCommand newStudentCommand = new NewStudentCommand();
        newStudentCommand.execute(input, tutorialClassList);

        // Check if student has been added
        Student student = tutorialClassList.getTutorialByName("T11")
                .getStudentList()
                .getStudentByMatricNumber("A2457654W");
        assertNotNull(student, "Student should be added successfully.");
        assertEquals("Mark", student.getName(), "Student's name should be 'Mark'.");
    }


}
