package command.markscommands;

import command.taskcommands.Command;
import exception.TASyncException;
import marks.Marks;
import marks.MarksList;
import students.Student;
import students.StudentList;
import tutorial.TutorialClass;
import tutorial.TutorialClassList;

/**
 * Represents the DELETEMARKS command to delete a marks entry for a given student.
 */
public class DeleteMarksCommand implements Command<TutorialClassList> {

    /**
     * Executes the command, retrieving the student and marks entry with the given assignmentName, and deleting it
     * from the student's marksList.
     * @param parts Comma separated parts from input string containing required student and marks details.
     * @param tutorialClassList List of tutorial classes containing students.
     **/
    @Override
    public void execute(String parts, TutorialClassList tutorialClassList) {
        assert parts != null;
        assert tutorialClassList != null;

        try {
            if (parts.isEmpty()){
                throw TASyncException.invalidDeleteMarksCommand();
            }

            String[] partsArray = parts.split(",");
            if (partsArray.length < 3){
                throw TASyncException.invalidDeleteMarksCommand();
            }

            String tutorialID = partsArray[0].trim();
            String matricNumber = partsArray[1].trim();
            String assignmentName = partsArray[2].trim();

            // Retrieve the tutorial class by its code
            TutorialClass tutorialClass = tutorialClassList.getTutorialByName(tutorialID);
            if (tutorialClass == null) {
                throw new TASyncException("No tutorial class found with code " + tutorialID);
            }

            StudentList studentList = tutorialClass.getStudentList();
            // Find the student by matric number
            Student student = studentList.getStudentByMatricNumber(matricNumber);
            if (student == null) {
                throw new TASyncException("No student found with matric number " + matricNumber);
            }

            MarksList marksList = student.getMarksList();
            Marks marksToDelete = marksList.getByAssignmentName(assignmentName);
            if (marksToDelete == null){
                throw new TASyncException("No marks found with assignment name " + assignmentName);
            }

            marksList.deleteMarks(marksToDelete);
            System.out.println("Marks successfully deleted.");
        } catch (TASyncException e) {
            System.out.println(e.getMessage());
        }
    }
}
