package command.tutorialcommands;

import tutorial.TutorialClass;
import tutorial.TutorialClassList;
import exception.TASyncException;
import students.Student;
import command.taskcommands.Command;

import java.util.ArrayList;

/**
 * Represents the "LIST_TUTORIAL_STUDENTS" command that lists all students enrolled in a specific tutorial class.
 * If the tutorial class is not found or the input is invalid, an appropriate error message is displayed.
 */
public class ListTutorialStudentsCommand implements Command<TutorialClassList> {

    /**
     * Executes the "LIST_TUTORIAL_STUDENTS" command by listing all students in a specific tutorial class.
     * The input string should contain the tutorial class name.
     * If the input is invalid or the tutorial class is not found, an appropriate error message is displayed.
     *
     * @param tutorialName The name of the tutorial class to list the students for.
     * @param tutorialClassList The tutorial class list to search within.
     */
    @Override
    // List all students in the given tutorial by tutorial name
    public void execute(String tutorialName, TutorialClassList tutorialClassList) {

        try {
            // Validate the input string (tutorial name)
            if (tutorialName == null || tutorialName.trim().isEmpty()) {
                throw TASyncException.invalidListTutorialStudentsCommand();
            }

            TutorialClass targetTutorial = null;
            ArrayList<TutorialClass> tutorialClasses = tutorialClassList.getTutorialClasses();

            if(tutorialClasses.isEmpty()) {
                System.out.println("There are no tutorial created yet.");
                return;
            }

            // Find the tutorial by name
            for (TutorialClass tutorial : tutorialClasses) {
                if (tutorial.getTutorialName().equals(tutorialName)) {
                    targetTutorial = tutorial;
                }
            }

            // If the tutorial name does not match any, throw an exception
            if (targetTutorial == null) {
                throw TASyncException.invalidListTutorialStudentsCommand();
            }

            ArrayList<Student> enrolledStudents = targetTutorial.getStudentList().getStudents();

            // Print the title
            System.out.println("List of students in tutorial " + targetTutorial.getTutorialName() + ":");

            // If no students are enrolled in the tutorial
            if (enrolledStudents.isEmpty()) {
                System.out.println(targetTutorial.getTutorialName() + " has no students");
            } else {
                System.out.println("List of enrolled students in " + targetTutorial.getTutorialName() + ":");
                Student.printStudentTable(enrolledStudents);
            }

            System.out.println();

        } catch (TASyncException e) {
            // Specific exception thrown by our TASyncException class
            System.out.println(e.getMessage());
        } 
    }
}
