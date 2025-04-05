package command.studentcommands;

import command.taskcommands.Command;
import exception.TASyncException;
import students.Student;
import students.StudentList;
import tutorial.TutorialClass;
import tutorial.TutorialClassList;

import static command.studentcommands.StudentCommandHelper.parseInput;

/**
 * Represents the "DELETESTUDENT" command that removes a student from a specific
 * tutorial class. The command searches for the student using the provided matric number
 * within the specified tutorial class and removes them if found.
 */
public class DeleteStudentCommand implements Command<TutorialClassList> {

    /**
     * Executes the "DELETESTUDENT" command by removing a student from the student list
     * of a specific tutorial class.
     *
     * <p>The input string should contain the tutorial class code and the matric number
     * of the student to be deleted.</p>
     *
     * <p>If the input is invalid, the tutorial class is not found, or the student is
     * not found, an appropriate error message is displayed. </p>
     *
     * @param parts The input string containing the tutorial class code and matric number
     *              (format: {@code <TutorialClassCode>,<MatricNumber>}).
     * @param tutorialClassList The tutorial class list provides the tutorial class which the student will be removed.
     */

    @Override
    public void execute(String parts, TutorialClassList tutorialClassList) {
        try {

            String[] inputParts = parseInput(parts,2);

            String tutorialClassCode = inputParts[0].trim();
            String matricNumber = inputParts[1].trim();

            // Retrieve the tutorial class by its code
            TutorialClass tutorialClass = tutorialClassList.getTutorialByName(tutorialClassCode);
            if (tutorialClass.getStartTime() == null) {
                throw new TASyncException("No tutorial class found with code: " + tutorialClassCode);
            }

            // Retrieve the student list from the tutorial class
            StudentList studentList = tutorialClass.getStudentList();

            // Find the student by matric number
            Student studentToRemove = studentList.getStudentByMatricNumber(matricNumber);
            if (studentToRemove != null) {
                // Remove the student
                studentList.removeStudent(studentToRemove);
                System.out.println("Student with matric number " + matricNumber
                        + " has been removed from tutorial class " + tutorialClassCode + ".");
            } else {
                System.out.println("No student found with matric number " + matricNumber
                        + " in tutorial class " + tutorialClassCode + ".");
            }
        } catch (TASyncException e) {
            System.out.println(e.getMessage());
        }
    }

}
