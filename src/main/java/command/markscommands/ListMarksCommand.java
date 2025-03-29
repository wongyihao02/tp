package command.markscommands;

import command.taskcommands.Command;
import exception.TASyncException;
import students.Student;
import students.StudentList;
import tutorial.TutorialClass;
import tutorial.TutorialClassList;

public class ListMarksCommand implements Command<TutorialClassList> {
    @Override
    public void execute(String parts, TutorialClassList tutorialClassList){
        assert parts != null;
        assert tutorialClassList != null;

        try {
            if (parts.isEmpty()){
                throw TASyncException.invalidListMarksCommand();
            }

            String[] partsArray = parts.split(",");
            if (partsArray.length < 2){
                throw TASyncException.invalidListMarksCommand();
            }

            String tutorialID = partsArray[0].trim();
            String matricNumber = partsArray[1].trim();

            // Retrieve the tutorial class by its code
            TutorialClass tutorialClass = tutorialClassList.getByName(tutorialID);
            if (tutorialClass == null) {
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
