package studentcommands;

import exception.TASyncException;
import students.Student;
import students.StudentList;
import taskCommands.Command;

/**
 * Represents the "DELETESTUDENT" command that removes a student from the student list.
 * This command expects the input to contain the student's matric number.
 * If the student is found, they are removed from the list; otherwise, an error message is displayed.
 */
public class DeleteStudentCommand implements Command<StudentList> {

    /**
     * Executes the "DELETESTUDENT" command by removing a student from the student list.
     * The input string should contain the matric number of the student to be deleted.
     * If the input is invalid or the student is not found, an appropriate error message is displayed.
     *
     * @param parts The input string containing the matric number of the student to be deleted.
     * @param studentList The student list from which the student will be removed.
     */
    @Override
    public void execute(String parts, StudentList studentList) {
        try {
            // Check if the input is a valid matric number
            if (parts != null && !parts.trim().isEmpty()) {
                Student studentToRemove = studentList.getStudentByMatricNumber(parts);
                if (studentToRemove != null) {
                    studentList.removeStudent(studentToRemove); // Remove the student
                    System.out.println("Student with matric number " + parts + " has been removed.");
                } else {
                    System.out.println("No student found with matric number " + parts + ".");
                }
            } else {
                throw TASyncException.invalidDeleteStudentCommand(); // Throw an exception if the input is invalid
            }
        } catch (Exception e) {
            System.out.println(e.getMessage()); // Handle any exceptions
        }
    }
}