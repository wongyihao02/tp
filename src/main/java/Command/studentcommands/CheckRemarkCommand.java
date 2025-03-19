package Command.studentcommands;

import exception.TASyncException;
import students.Student;
import students.StudentList;
import Command.taskCommands.Command;


public class CheckRemarkCommand implements Command<StudentList> {

    /**
     * Executes the "CHECK_REMARK" command to display the remarks for a specific student.
     *
     * @param parts      The input string containing the student's matric number.
     * @param studentList The student list to search within.
     */
    @Override
    public void execute(String parts, StudentList studentList) {
        try {

            // Check if the input is valid
            if (parts == null || parts.trim().isEmpty()) {
                throw TASyncException.invalidCheckRemarkCommand();
            }

            // Search for the student by matric number only
            Student student = studentList.getStudentByMatricNumber(parts);

            // If the student is found, display their remarks
            if (student != null) {
                String remark = student.getRemark();
                if (remark == null || remark.trim().isEmpty()) {
                    System.out.println("No remarks found for student: " + student.getName());
                } else {
                    System.out.println("Remarks for " + student.getName() + ": " + remark);
                }
            } else {
                System.out.println("No student found with matric number: " + parts);
            }
        } catch (TASyncException e) {
            System.out.println(e.getMessage());
        }
    }
}
