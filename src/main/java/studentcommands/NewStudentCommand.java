package studentcommands;

import Util.DateTimeFormatterUtil;
import exception.TASyncException;
import students.Student;
import students.StudentList;
import taskCommands.Command;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NewStudentCommand implements Command<StudentList> {
    private static final Logger logger = Logger.getLogger(NewStudentCommand.class.getName());

    @Override
    public void execute(String parts, StudentList studentList) {
        try {
            if (parts == null || parts.trim().isEmpty()) {
                throw new TASyncException("Invalid input: Student details cannot be empty.");
            }

            String[] studentParts = parts.split(",");
            if (studentParts.length != 6) {
                throw TASyncException.invalidNewStudentCommand();
            }

            String studentName = studentParts[0].trim();
            if (studentName.isEmpty()) {
                throw new TASyncException("Invalid input: Student name cannot be empty.");
            }

            LocalDate dob = DateTimeFormatterUtil.parseDate(studentParts[1].trim());
            if (dob == null) {
                throw new TASyncException("Please enter a valid dob.");
            }

            String gender = studentParts[2].trim();
            if (!gender.equalsIgnoreCase("Male") && !gender.equalsIgnoreCase("Female")) {
                throw new TASyncException("Invalid input: Gender must be 'Male' or 'Female'.");
            }

            String contact = studentParts[3].trim();
            if (!contact.matches("\\d{8}")) {
                throw new TASyncException("Invalid contact number: Must be 8 digits.");
            }

            String matricNumber = studentParts[4].trim();
            if (!matricNumber.matches("A\\d{7}[A-Z]")) {
                throw new TASyncException("Invalid matric number: Must follow the format A1234567X.");
            }

            // **Check if the matric number already exists**
            if (studentList.getStudentByMatricNumber(matricNumber) != null) {
                throw new TASyncException("Duplicate matric number: A student with this matric number already exists.");
            }

            String tutorialClass = studentParts[5].trim();
            if (tutorialClass.isEmpty()) {
                throw TASyncException.invalidNewStudentCommand();
            }

            // Create and add student
            Student student = new Student(studentName, dob, gender, contact, matricNumber, tutorialClass);
            studentList.addStudent(student);
            logger.log(Level.INFO, "New student added: " + studentName);
            System.out.println("New student added: " + studentName);

        } catch (TASyncException e) {
            System.out.println(e.getMessage());
        }
    }
}
