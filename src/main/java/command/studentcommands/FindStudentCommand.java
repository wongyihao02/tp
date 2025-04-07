package command.studentcommands;

import exception.TASyncException;
import students.Student;
import students.StudentList;
import tutorial.TutorialClass;
import tutorial.TutorialClassList;
import command.taskcommands.Command;

/**
 * Represents the "FIND_STUDENT" command that searches for students by partial matching of name or matric number
 * within a tutorial class list. The command displays the students that match the keyword.
 */
public class FindStudentCommand implements Command<TutorialClassList> {


    /**
     * Executes the "FIND_STUDENT" command by searching for students that partially match the given keyword
     * across all tutorial classes.
     * It prints the matching students to the user.
     *
     * @param parts The keyword used to search for students (name or matric number).
     * @param tutorialClassList The list of tutorial classes to search within.
     */
    @Override
    public void execute(String parts, TutorialClassList tutorialClassList) {
        try {

            // Check if the input is valid
            if (parts == null || parts.trim().isEmpty()) {
                throw TASyncException.invalidFindStudentCommand();
            }

            // Variable to track whether any student is found
            boolean studentFound = false;

            // Iterate through each tutorial class
            for (TutorialClass tutorialClass : tutorialClassList.getTutorialClasses()) {
                // Get the student list from the tutorial class
                StudentList studentList = tutorialClass.getStudentList();

                // Search for students by name or matric number using partial matching
                boolean foundInThisClass = false;
                for (Student student : studentList.getStudents()) {
                    assert student.getName() != null : "Student name should not be null";
                    assert student.getMatricNumber() != null : "Student matric number should not be null";
                    boolean nameMatches = student.getName().toLowerCase().contains(parts.toLowerCase());
                    boolean matricMatches = student.getMatricNumber().toLowerCase().contains(parts.toLowerCase());

                    if (nameMatches || matricMatches) {
                        if (!foundInThisClass) {
                            // If student is found, print the tutorial class name first
                            studentFound = true;
                            System.out.println("In tutorial " + tutorialClass.getTutorialName() + ":");
                            foundInThisClass = true;
                        }
                        // Print the student details
                        System.out.println(formatStudentDetails(student));
                    }
                }
            }

            // If no students are found across all tutorial classes
            if (!studentFound) {
                System.out.println("No students found with the keyword: " + parts);
            }
        } catch (TASyncException e) {
            System.out.println(e.getMessage());
        }
    }

    private String formatStudentDetails(Student student) {
        // Concatenate the student details in one line, separated by commas
        return String.format("Name: %s, Matric Number: %s, Date of Birth: %s, Gender: %s, Contact: %s",
                student.getName(), student.getMatricNumber(), student.getDateOfBirth(),
                student.getGender(), student.getContact());
    }
}
