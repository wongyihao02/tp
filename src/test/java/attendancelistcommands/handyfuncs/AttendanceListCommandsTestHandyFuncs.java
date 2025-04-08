package attendancelistcommands.handyfuncs;

import attendance.AttendanceFile;
import attendance.AttendanceList;
import students.Student;
import students.StudentList;
import tutorial.TutorialClass;
import tutorial.TutorialClassList;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class AttendanceListCommandsTestHandyFuncs {



    /**
     * Initializes a TutorialClassList with predefined tutorial classes and students.
     * @return A populated TutorialClassList.
     */
    public static TutorialClassList initializeTutorialClasses() {
        TutorialClassList tutorialClassList = new TutorialClassList();

        // Create new TutorialClass objects
        TutorialClass tutorial1 = createTutorial("T01", LocalTime.of(10, 0), LocalTime.of(12, 0));
        TutorialClass tutorial2 = createTutorial("T02", LocalTime.of(14, 0), LocalTime.of(16, 0));
        TutorialClass tutorial3 = createTutorial("T03", LocalTime.of(18, 0), LocalTime.of(19, 0));

        // Add students to tutorial1
        Student student1 = new Student("Alice Tan", LocalDate.of(2002, 5, 14), "Female", "91234567", "A001");
        Student student2 = new Student("Bob Lim", LocalDate.of(2001, 3, 22), "Male", "92345678", "A002");
        Student student3 = new Student("Kim Dokja", LocalDate.of(2002, 2, 15), "Male", "92345678", "A003");
        Student student4 = new Student("Han sooyung", LocalDate.of(2002, 2, 15), "Female", "92345678", "A490");
        Student student5 = new Student("Person 4", LocalDate.of(2005, 10, 31), "Male", "92345678", "A948");
        Student student6 = new Student("Person 5", LocalDate.of(2000, 5, 20), "Female", "92345678", "A287");
        Student student7 = new Student("Roselle Gustave Bonaparte", LocalDate.of(1998, 1, 11),
                "Male", "92345678", "A333");
        tutorial1.getStudentList().addStudent(student1);
        tutorial1.getStudentList().addStudent(student2);
        tutorial1.getStudentList().addStudent(student3);
        tutorial1.getStudentList().addStudent(student7);


        tutorial2.getStudentList().addStudent(student3);
        tutorial2.getStudentList().addStudent(student4);
        tutorial2.getStudentList().addStudent(student5);
        tutorial2.getStudentList().addStudent(student6);
        tutorial2.getStudentList().addStudent(student7);

        // Add tutorial classes to list
        tutorialClassList.addTutorialClass(tutorial1);
        tutorialClassList.addTutorialClass(tutorial2);
        tutorialClassList.addTutorialClass(tutorial3);

        return tutorialClassList;
    }

    public static AttendanceFile initializeAttendanceFile() {
        TutorialClassList tutorialClassList = initializeTutorialClasses();
        TutorialClass class1 = tutorialClassList.getTutorialByName("T01");
        Student roselleGustaveBonaparte = class1.getStudentList().getStudentByName("Roselle Gustave Bonaparte");
        Student kimDokja = class1.getStudentList().getStudentByName("Kim Dokja");
        AttendanceList first = new AttendanceList(tutorialClassList.getTutorialByName("T01"), 1);
        AttendanceList second = new AttendanceList(tutorialClassList.getTutorialByName("T01"), 2);
        AttendanceList third = new AttendanceList(tutorialClassList.getTutorialByName("T01"), 3);
        AttendanceList four = new AttendanceList(tutorialClassList.getTutorialByName("T01"), 4);
        AttendanceList five = new AttendanceList(tutorialClassList.getTutorialByName("T02"), 1);
        AttendanceList sixth = new AttendanceList(tutorialClassList.getTutorialByName("T02"), 2);
        AttendanceList seventh = new AttendanceList(tutorialClassList.getTutorialByName("T02"), 3);
        AttendanceList eighth = new AttendanceList(tutorialClassList.getTutorialByName("T02"), 6);
        AttendanceList ninth = new AttendanceList(tutorialClassList.getTutorialByName("T02"), 7);
        AttendanceList tenth = new AttendanceList(tutorialClassList.getTutorialByName("T03"), 1);
        ArrayList<String> roselleComments = new ArrayList<>();
        ArrayList<String> kimComments = new ArrayList<>();
        roselleComments.add("Savant transmigrator");
        kimComments.add("ugly squid");
        first.addComments(roselleGustaveBonaparte, roselleComments);
        first.addComments(kimDokja, kimComments);
        ArrayList<AttendanceList> list = new ArrayList<>();
        list.add(first);
        list.add(seventh);
        list.add(eighth);
        list.add(ninth);
        list.add(second);
        list.add(third);
        list.add(four);
        list.add(five);
        list.add(sixth);
        list.add(tenth);
        return new AttendanceFile(list);
    }

    /**
     * Creates a tutorial class with a given name, week number, start time, and end time.
     * @param tutorialName The name of the tutorial class.
     * @param startTime The start time of the tutorial.
     * @param endTime The end time of the tutorial.
     * @return A new TutorialClass instance.
     */
    public static TutorialClass createTutorial(
            String tutorialName,
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
