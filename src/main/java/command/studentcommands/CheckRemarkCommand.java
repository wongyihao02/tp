package command.studentcommands;

import exception.TASyncException;
import students.Student;
import students.StudentList;
import tutorial.TutorialClass;
import tutorial.TutorialClassList;
import command.taskcommands.Command;

import static command.studentcommands.StudentCommandHelper.parseInput;

/**
 * Represents the "CHECK_REMARK" command that retrieves and displays the remark
 * of a specific student in a tutorial class. The command searches for the student
 * using the provided matric number within the specified tutorial class and
 * displays their remark if found.
 */
public class CheckRemarkCommand implements Command<TutorialClassList> {

    /**
     * Executes the "CHECK_REMARK" command to display the remarks for a specific student
     * in a tutorial class. The input string should contain the tutorial class code and
     * the matric number of the student (format: [TutorialClassCode], [MatricNumber]).
     * If the input is invalid, the tutorial class is not found, or the student is not found,
     * an appropriate error message is displayed.
     *
     * @param parts             The input string containing the tutorial class code and matric number.
     * @param tutorialClassList The tutorial class list to search within.
     */
    @Override
    public void execute(String parts, TutorialClassList tutorialClassList) {
        try {

            String[] inputParts = parseInput(parts, 2);
            assert inputParts.length == 2 : "Expected 2 parts: tutorial class code and matric number";

            String tutorialClassCode = inputParts[0].trim();
            String matricNumber = inputParts[1].trim();

            // Retrieve the tutorial class by its code
            TutorialClass tutorialClass = tutorialClassList.getTutorialByName(tutorialClassCode);
            if (tutorialClass.getStartTime() == null) {
                throw TASyncException.invalidCheckRemarkCommand();
            }

            // Retrieve the student list from the tutorial class
            StudentList studentList = tutorialClass.getStudentList();

            // Search for the student by matric number
            Student student = studentList.getStudentByMatricNumber(matricNumber);
            if (student == null) {
                throw TASyncException.invalidCheckRemarkCommand();
            }

            String remark = student.getRemark();
            if (remark == null || remark.trim().isEmpty()) {
                System.out.println("No remarks found for student: " + student.getName());
            } else {
                System.out.println("Remarks for " + student.getName() + ": " + remark);
            }

        } catch (TASyncException e) {
            System.out.println(e.getMessage());
        }
    }
}

