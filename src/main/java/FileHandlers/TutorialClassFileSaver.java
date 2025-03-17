package FileHandlers;

import students.Student;
import Tutorial.TutorialClass;
import Util.DateTimeFormatterUtil;

import java.io.FileWriter;
import java.io.IOException;

public class TutorialClassFileSaver implements FileSaver<TutorialClass> {
    private final StudentFileSaver studentSaver = new StudentFileSaver(); // not strictly needed here

    @Override
    public void saveToFile(TutorialClass tutorial, String directoryPath) {
        String fileName = directoryPath + "/" + tutorial.getTutorialName() + ".csv";

        try (FileWriter writer = new FileWriter(fileName)) {
            // Write metadata
            writer.write(tutorial.getTutorialName() + "," +
                    tutorial.getDayOfWeek().toString() + "," +
                    tutorial.getStartTime().toString() + "," +
                    tutorial.getEndTime().toString() + "\n");

            // Write each student
            for (Student student : tutorial.getStudentList().getStudents()) {
                writer.write(student.getName() + "," +
                        DateTimeFormatterUtil.formatDate(student.getDateOfBirth()) + "," +
                        student.getGender() + "," +
                        student.getContact() + "," +
                        student.getMatricNumber() + "," +
                        (student.getRemark() != null ? student.getRemark() : "") + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error saving tutorial class: " + e.getMessage());
        }
    }
}