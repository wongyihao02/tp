package Command.tutorialCommands;

import Tutorial.TutorialClass;
import Tutorial.TutorialClassList;
import exception.TASyncException;
import students.Student;
import Command.taskCommands.Command;

import java.util.ArrayList;

public class ListTutorialStudentsCommand implements Command<TutorialClassList> {

    @Override
    // List all students in the given tutorial by tutorial name
    public void execute(String tutorialName, TutorialClassList tutorialClassList) {

        try {
            // Validate the input string (tutorial name)
            if (tutorialName == null || tutorialName.trim().isEmpty()) {
                throw TASyncException.invalidListTutorialStudentsCommand();
            }

            TutorialClass targetTutorial = null;
            ArrayList<TutorialClass> tutorials = tutorialClassList.getTutorialClasses();

            // Find the tutorial by name
            for (TutorialClass tutorial : tutorials) {
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
                for (Student student : enrolledStudents) {
                    System.out.println(student.toString());
                }
            }

            System.out.println();

        } catch (TASyncException e) {
            // Specific exception thrown by our TASyncException class
            System.out.println("TASyncException: " + e.getMessage());
        } 
    }
}
