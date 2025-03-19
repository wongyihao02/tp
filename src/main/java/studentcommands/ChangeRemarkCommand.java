package studentcommands;

import exception.TASyncException;
import students.Student;
import students.StudentList;
import taskCommands.Command;
import java.util.logging.*;

/**
 * Represents the "CHANGE_REMARK" command that updates the remark of a student.
 */
public class ChangeRemarkCommand implements Command<StudentList> {

    /**
     * Executes the "CHANGE_REMARK" command by updating the remark of a student.
     *
     * @param parts The input containing the matric number and the new remark (format: "matricNumber newRemark").
     * @param studentList The student list where the student's remark will be updated.
     */
    @Override
    public void execute(String parts, StudentList studentList) {
        // Assertions for preconditions
        assert parts != null : "Input 'parts' cannot be null";
        assert !parts.trim().isEmpty() : "Input 'parts' cannot be empty";
        assert studentList != null : "StudentList cannot be null";

        try {

            // Split the input into matric number and new remark
            String[] partsArray = parts.split(" ", 2); // Split into 2 parts: matricNumber and remark
            if (partsArray.length < 2) {
                throw TASyncException.invalidChangeRemarkCommand();
            }

            String matricNumber = partsArray[0].trim();
            String newRemark = partsArray[1].trim();


            // Find the student by matric number
            Student student = studentList.getStudentByMatricNumber(matricNumber);
            if (student == null) {
                System.out.println("No student found with matric number: " + matricNumber);
                return;
            }

            // Update the student's remark
            student.setRemark(newRemark);
            System.out.println("Remark updated for student with matric number " + matricNumber + ".");
        } catch (TASyncException e) {
            System.out.println(e.getMessage());
        }
    }
}