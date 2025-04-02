package studentcommands.utils;

import students.Student;
import students.StudentList;
import tutorial.TutorialClass;
import tutorial.TutorialClassList;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.time.LocalTime;

public class StudentCommandTestUtil {

    /**
     * Initializes a TutorialClassList with predefined tutorial classes and students.
     * @return A populated TutorialClassList.
     */
    public static TutorialClassList initializeTutorialClasses() {
        TutorialClassList tutorialClassList = new TutorialClassList();

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

        return tutorialClassList;
    }

    /**
     * Creates a tutorial class with a given name, week number, start time, and end time.
     * @param tutorialName The name of the tutorial class.
     * @param weekNumber The week number of the tutorial.
     * @param startTime The start time of the tutorial.
     * @param endTime The end time of the tutorial.
     * @return A new TutorialClass instance.
     */
    public static TutorialClass createTutorial(
            String tutorialName,
            int weekNumber,
            LocalTime startTime,
            LocalTime endTime) {

        TutorialClass tutorial = new TutorialClass();
        tutorial.setTutorialName(tutorialName);
        tutorial.setStartTime(startTime);
        tutorial.setEndTime(endTime);
        tutorial.setStudentList(new StudentList());

        return tutorial;
    }


    /**
     * Captures System.out for testing console output.
     * @return A ByteArrayOutputStream to capture printed output.
     */
    public static ByteArrayOutputStream captureSystemOut() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        return outputStream;
    }
}
