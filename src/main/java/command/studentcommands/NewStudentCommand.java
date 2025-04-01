package command.studentcommands;

import util.DateTimeFormatterUtil;
import exception.TASyncException;
import students.Student;
import command.taskcommands.Command;
import tutorial.TutorialClass;
import tutorial.TutorialClassList;
import java.time.LocalDate;

/**
 * Represents the "NEWSTUDENT" command that adds a new student to a specified tutorial class.
 * The command validates the student's details and ensures no duplicate matric number exists
 * before adding the student to the class.
 */
public class NewStudentCommand implements Command<TutorialClassList> {

    /**
     * Executes the "NEWSTUDENT" command by creating a new student and adding them
     * to the student list of a specific tutorial class.
     *
     * <p>The input string must contain the student's name, date of birth, gender, contact number,
     * matric number, and tutorial class code in the format:
     * {@code <StudentName>,<DOB>,<Gender>,<ContactNumber>,<MatricNumber>,<TutorialClassCode>}.</p>
     *
     * <p>If the input is invalid, the tutorial class is not found, or a student with the same
     * matric number already exists, an appropriate error message is displayed.</p>
     *
     * @param parts The input string containing student details.
     * @param tutorialClassList The tutorial class list that provides the tutorial class
     *                          in which the student will be added.
     */
    @Override
    public void execute(String parts, TutorialClassList tutorialClassList) {
        try {
            // Validate input
            if (parts == null || parts.trim().isEmpty()) {
                throw TASyncException.invalidNewStudentCommand();
            }

            // Split the input into parts
            String[] studentParts = parts.split(",");
            if (studentParts.length != 6) {
                throw TASyncException.invalidNewStudentCommand();
            }

            // Extract and validate student details
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

            String tutorialClassCode = studentParts[5].trim();
            if (tutorialClassCode.isEmpty()) {
                throw TASyncException.invalidNewStudentCommand();
            }

            // Retrieve the TutorialClass by its code
            TutorialClass tutorialClass = tutorialClassList.getTutorialByName(tutorialClassCode);
            if (tutorialClass.getStartTime() == null) {
                throw new TASyncException("No tutorial class found with code: " + tutorialClassCode);
            }

            // Check if the matric number already exists in the tutorial class
            if (tutorialClass.getStudentList().getStudentByMatricNumber(matricNumber) != null) {
                throw new TASyncException("Duplicate matric number: A student with this matric number already exists.");
            }

            // Create and add the student to the tutorial class
            Student student = new Student(studentName, dob, gender, contact, matricNumber);
            tutorialClass.getStudentList().addStudent(student);

            // Log and display success message
            System.out.println("New student added to tutorial class " + tutorialClassCode + ": " + studentName);

        } catch (TASyncException e) {
            // Handle TASyncException
            System.out.println("Error: " + e.getMessage());
        }
    }
}
