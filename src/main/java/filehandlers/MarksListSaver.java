package filehandlers;

import marks.Marks;
import marks.MarksList;
import students.Student;
import tutorial.TutorialClass;
import tutorial.TutorialClassList;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class MarksListSaver implements FileSaver<TutorialClassList> {
    private static final String FILE_PATH = "./data/marks.txt";
    private static final String DIRECTORY_PATH = "./data";

    @Override
    public void saveToFile(TutorialClassList tutorialClassList, String directoryPath) {
        // Create the file if it doesn't exist
        FileCreator.createFileIfNotExists(FILE_PATH, DIRECTORY_PATH);

        // Clear the file before writing new data
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            writer.write(""); // Clears the file
        } catch (IOException e) {
            System.out.println("Error clearing file: " + e.getMessage());
        }

        for (TutorialClass tutorialClass: tutorialClassList.getTutorialClasses()){
            for (Student student: tutorialClass.getStudentList().getStudents()){
                saveMarks(tutorialClass.getTutorialName(), student.getMatricNumber(), student.getMarksList());
            }
        }

    }

    public void saveMarks(String tutorialID, String matric, MarksList marksList){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            for (Marks marks: marksList.getMarksList()) {
                writer.write(tutorialID + "," + matric + "," + marks.toFileFormat());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving marks: " + e.getMessage());
        }
    }
}
