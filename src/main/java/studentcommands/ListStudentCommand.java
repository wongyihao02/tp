package studentcommands;

import students.Student;
import students.StudentList;
import taskCommands.Command;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ListStudentCommand implements Command<StudentList> {
    // Logger instance for this class
    private static final Logger logger = Logger.getLogger(ListStudentCommand.class.getName());

    /**
     * Executes the "LIST_STUDENT" command by printing all students in the StudentList.
     *
     * @param parts      The input string (unused in this command).
     * @param studentList The student list to display.
     */
    @Override
    public void execute(String parts, StudentList studentList) {
        try {
            // Log the start of the command execution
            logger.log(Level.INFO, "Executing LIST_STUDENT command");

            // Retrieve the list of students
            List<Student> students = studentList.getStudents();

            // Check if the list is empty
            if (students.isEmpty()) {
                logger.log(Level.INFO, "No students found in the list");
                System.out.println("No students found.");
                return;
            }

            // Print all students
            logger.log(Level.INFO, "Displaying all students");
            System.out.println("List of students:");
            for (Student student : students) {
                System.out.println(student);
            }
        } catch (Exception e) {
            // Log any unexpected errors
            logger.log(Level.SEVERE, "Error executing LIST command: " + e.getMessage());
            System.out.println("An error occurred while listing students.");
        }
    }
}