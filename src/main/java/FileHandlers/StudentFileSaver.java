package FileHandlers;

import students.Student;
import Util.DateTimeFormatterUtil;

import java.io.FileWriter;
import java.io.IOException;

public class StudentFileSaver implements FileSaver<Student> {
    @Override
    public void saveToFile(Student student, String directoryPath) {
        String fileName = directoryPath + "/" + student.getMatricNumber() + ".csv";

        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write(student.getName() + ",");
            writer.write(DateTimeFormatterUtil.formatDate(student.getDateOfBirth()) + ",");
            writer.write(student.getGender() + ",");
            writer.write(student.getContact() + ",");
            writer.write(student.getMatricNumber() + ",");
            writer.write((student.getRemark() != null ? student.getRemark() : "")+ "\n");
        } catch (IOException e) {
            System.out.println("Error saving student: " + e.getMessage());
        }
    }
}