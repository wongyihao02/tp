package command.markscommands;

import command.taskcommands.Command;
import exception.TASyncException;
import students.Student;
import students.StudentList;
import tutorial.TutorialClass;
import tutorial.TutorialClassList;

/**
 * Represents the list -m command to list marks for all assignments for a given student.
 */
public class ListMarksCommand implements Command<TutorialClassList> {

    /**
     * Executes the command, retrieving the given student and printing all marks.
     * @param parts Comma separated parts from input string containing student details.
     * @param tutorialClassList List of tutorial classes containing students.
     */
    @Override
    public void execute(String parts, TutorialClassList tutorialClassList){
        assert parts != null;
        assert tutorialClassList != null;

        try {
            if (parts.isEmpty()){
                System.out.println(parts);
                throw TASyncException.invalidListMarksCommand();
            }

            String[] partsArray = parts.split(",");
            if (partsArray.length < 2){
                throw TASyncException.invalidListMarksCommand();
            }

            String tutorialID = partsArray[0].trim();
            String matricNumber = partsArray[1].trim();

            // Retrieve the tutorial class by its code
            TutorialClass tutorialClass = tutorialClassList.getTutorialByName(tutorialID);
            if (tutorialClass.getStartTime() == null) {
                throw new TASyncException("No tutorial class found with code " + tutorialID);
            }

            StudentList studentList = tutorialClass.getStudentList();
            // Find the student by matric number
            Student student = studentList.getStudentByMatricNumber(matricNumber);
            if (student == null) {
                throw new TASyncException("No student found with matric number " + matricNumber);
            }

            assert student.getMatricNumber().equals(matricNumber);
            System.out.println("Marks for student: " + student.getName());
            student.getMarksList().printMarks();
        } catch (TASyncException e) {
            System.out.println(e.getMessage());
        }
    }
}
