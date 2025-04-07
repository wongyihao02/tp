package command.studentcommands;

import command.taskcommands.Command;
import exception.TASyncException;
import students.Student;
import students.StudentList;
import tutorial.TutorialClass;
import tutorial.TutorialClassList;

import static command.studentcommands.StudentCommandHelper.parseInput;

/**
 * Represents the "CHANGE_REMARK" command that updates the remark of a student
 * in a specific tutorial class. The command searches for the student using
 * the provided matric number within the specified tutorial class and modifies
 * their remark accordingly.
 */
public class ChangeRemarkCommand implements Command<TutorialClassList> {

    /**
     * Executes the "CHANGE_REMARK" command by updating the remark of a student in a specific tutorial class.
     * The input string should contain the tutorial class code, matric number, and new remark.
     *
     * @param parts The input string containing the tutorial class code, matric number, and new remark.
     * @param tutorialClassList The tutorial class list to search within.
     */
    @Override
    public void execute(String parts, TutorialClassList tutorialClassList) {
        try {
            String[] partsArray = parseInput(parts,3);
            assert partsArray.length == 3 : "Expected 3 parts: class code, matric number, and remark";

            String tutorialClassCode = partsArray[0].trim();
            String matricNumber = partsArray[1].trim();
            String newRemark = partsArray[2].trim();

            TutorialClass tutorialClass = tutorialClassList.getTutorialByName(tutorialClassCode);
            if (tutorialClass.getStartTime() == null) {
                throw TASyncException.invalidChangeRemarkCommand();
            }

            StudentList studentList = tutorialClass.getStudentList();
            Student student = studentList.getStudentByMatricNumber(matricNumber);
            if (student == null) {
                throw new TASyncException("No student found with matric number: " + matricNumber);
            }

            student.setRemark(newRemark);
            assert student.getRemark().equals(newRemark) : "Remark was updated correctly";
            System.out.println("Remark updated for student with matric number " +
                    matricNumber + " in tutorial class " + tutorialClassCode + ".");
        } catch (TASyncException e) {
            System.out.println(e.getMessage());
        }
    }

}
