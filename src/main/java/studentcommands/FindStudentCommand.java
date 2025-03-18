package studentcommands;

import exception.TASyncException;
import students.Student;
import students.StudentList;
import taskCommands.Command;

/**
 * Represents the "FIND_STUDENT" command that searches for students by name or matric number.
 * The command displays the students that match the keyword.
 */
public class FindStudentCommand implements Command<StudentList> {

    /**
     * Executes the "FIND_STUDENT" command by searching for students that match the given keyword.
     * It prints the matching students to the user.
     *
     * @param parts The keyword used to search for students (name or matric number).
     * @param studentList The student list to search within.
     */
    @Override
    public void execute(String parts, StudentList studentList) {
        try {
            if (!parts.isEmpty()) {
                // Search for students by name or matric number
                Student studentByName = studentList.getStudentByName(parts);
                Student studentByMatric = studentList.getStudentByMatricNumber(parts);

                if (studentByName != null || studentByMatric != null) {
                    System.out.println("Matching students:");
                    if (studentByName != null) {
                        System.out.println(studentByName);
                    }
                    if (studentByMatric != null && !studentByMatric.equals(studentByName)) {
                        System.out.println(studentByMatric);
                    }
                } else {
                    System.out.println("No students found with the keyword: " + parts);
                }
            } else {
                throw TASyncException.invalidFindStudentCommand();
            }
        } catch (TASyncException e) {
            System.out.println(e.getMessage());
        }
    }
}