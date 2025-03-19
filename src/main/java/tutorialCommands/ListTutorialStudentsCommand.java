package tutorialCommands;

import Tutorial.TutorialClass;
import Tutorial.TutorialClassList;
import exception.TASyncException;
import students.Student;
import taskCommands.Command;

import java.util.ArrayList;

public class ListTutorialStudentsCommand implements Command<TutorialClassList> {

    @Override
    //list all students in the given tutorial name
    public void execute(String parts, TutorialClassList list) {

        try {
            //if empty input string
            if (parts == null || parts.trim().isEmpty()) {
                throw TASyncException.invalidListTutorialStudentsCommand();
            }

            TutorialClass theOne = null;
            ArrayList<TutorialClass> theList = list.getTutorialClasses();

            for (TutorialClass tc : theList) {
                if (tc.getTutorialName().equals(parts)) {
                    theOne = tc;
                }
            }

            //if input string does not correspond to any tutorial session
            if (theOne == null) {
                throw TASyncException.invalidListTutorialStudentsCommand();
            }


            ArrayList<Student> listOfStudents = theOne.getStudentList().getStudents();

            //title
            System.out.println("List of students in tutorial " + theOne.getTutorialName() + ":");

            //if tutorial session no one
            if (listOfStudents.isEmpty()) {
                System.out.println(theOne.getTutorialName() + " has no students");
            }else {
                for (Student s : listOfStudents) {
                    System.out.println(s.toString());
                }
            }

            System.out.println();

        } catch (TASyncException e) {
            System.out.println(e.getMessage());
        }
    }
}
