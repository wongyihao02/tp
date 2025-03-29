package command.markscommands;

import command.taskcommands.Command;
import exception.TASyncException;
import marks.Marks;
import students.Student;
import students.StudentList;
import tutorial.TutorialClass;
import tutorial.TutorialClassList;

public class AddMarksCommand implements Command<TutorialClassList> {
    @Override
    public void execute(String parts, TutorialClassList tutorialClassList){
        assert parts != null;
        assert tutorialClassList != null;

        try {
            if (parts.isEmpty()){
                throw TASyncException.invalidNewMarksCommand();
            }

            String[] partsArray = parts.split(",");
            if (partsArray.length < 5){
                throw TASyncException.invalidNewMarksCommand();
            }

            String tutorialID = partsArray[0].trim();
            String matricNumber = partsArray[1].trim();
            String assignmentName = partsArray[2].trim();
            int marks = Integer.parseInt(partsArray[3].trim());
            int maxMark = Integer.parseInt(partsArray[4].trim());

            if (marks > maxMark || marks < 0){
                throw new TASyncException("Marks achieved cannot be negative or more than maximum marks.");
            }

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
            Marks newMarks = new Marks(assignmentName, marks, maxMark);
            student.getMarksList().addMarks(newMarks);
            System.out.println("Marks added for student: " + student.getName());
            System.out.println(newMarks.toString());

        } catch (TASyncException e){
            System.out.println(e.getMessage());
        } catch (NumberFormatException e){
            System.out.println("Please enter a valid integer for marks.");
        }
    }
}
