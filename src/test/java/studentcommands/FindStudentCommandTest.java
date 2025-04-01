package studentcommands;


import command.studentcommands.FindStudentCommand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import students.Student;
import students.StudentList;
import tutorial.TutorialClass;
import tutorial.TutorialClassList;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class FindStudentCommandTest {
    private TutorialClassList tutorialClassList;
    private ByteArrayOutputStream outputStream;

    public static TutorialClass createTutorial(String name, int dayOfWeek, LocalTime startTime, LocalTime endTime) {
        TutorialClass tutorial = new TutorialClass();
        tutorial.setTutorialName(name);
        tutorial.setDayOfWeek(DayOfWeek.of(dayOfWeek));  // Convert int to DayOfWeek
        tutorial.setStartTime(startTime);
        tutorial.setEndTime(endTime);
        tutorial.setStudentList(new StudentList());  // Empty student list
        return tutorial;
    }

    @BeforeEach
    void setUp() {
        tutorialClassList = new TutorialClassList();

        // Create new TutorialClass objects
        TutorialClass tutorial1 = createTutorial("CS1010S", 2, LocalTime.of(10, 0), LocalTime.of(12, 0));
        TutorialClass tutorial2 = createTutorial("CS2040", 4, LocalTime.of(14, 0), LocalTime.of(16, 0));


        // Add students to tutorial1
        Student student1 = new Student("Alice Tan", LocalDate.of(2002, 5, 14), "Female", "91234567", "A1234567W");
        Student student2 = new Student("Bob Lim", LocalDate.of(2001, 3, 22), "Male", "92345678", "A1234567X");
        tutorial1.getStudentList().addStudent(student1);
        tutorial1.getStudentList().addStudent(student2);

        // Add students to tutorial2
        Student student3 = new Student("Alice Lee", LocalDate.of(2003, 7, 10), "Female", "93456789", "A1234567Z");
        tutorial2.getStudentList().addStudent(student3);

        // Add tutorial classes to list
        tutorialClassList.addTutorialClass(tutorial1);
        tutorialClassList.addTutorialClass(tutorial2);

        // Capture System.out to check command output
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }


    @Test
    void testFindStudentByExactName() {
        FindStudentCommand command = new FindStudentCommand();
        command.execute("Alice Tan", tutorialClassList);

        String output = outputStream.toString().trim();
        assertTrue(output.contains("In tutorial CS1010S:"));
        assertTrue(output.contains("Alice Tan"));
        assertTrue(output.contains("A1234567W"));
    }

    @Test
    void testFindStudentByPartialName() {
        FindStudentCommand command = new FindStudentCommand();
        command.execute("Alice", tutorialClassList);

        String output = outputStream.toString().trim();
        assertTrue(output.contains("In tutorial CS1010S:"));
        assertTrue(output.contains("Alice Tan"));
        assertTrue(output.contains("matricNumber=A1234567W"));

        assertTrue(output.contains("In tutorial CS2040:"));
        assertTrue(output.contains("Alice Lee"));
        assertTrue(output.contains("matricNumber=A1234567Z"));
    }

    @Test
    void testFindStudentByMatricNumber() {
        FindStudentCommand command = new FindStudentCommand();
        command.execute("A1234567X", tutorialClassList);

        String output = outputStream.toString().trim();
        assertTrue(output.contains("In tutorial CS1010S:"));
        assertTrue(output.contains("Bob Lim"));
        assertTrue(output.contains("matricNumber=A1234567X"));
    }

    @Test
    void testFindStudentNotFound() {
        FindStudentCommand command = new FindStudentCommand();
        command.execute("Charlie", tutorialClassList);

        String output = outputStream.toString().trim();
        assertTrue(output.contains("No students found with the keyword: Charlie"));
    }
}
