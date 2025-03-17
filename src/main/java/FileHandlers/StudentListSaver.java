package FileHandlers;

import students.Student;
import students.StudentList;

import java.io.File;

public class StudentListSaver implements FileSaver<StudentList> {
    private final StudentFileSaver studentSaver = new StudentFileSaver();

    @Override
    public void saveToFile(StudentList studentList, String directoryPath) {
        File dir = new File(directoryPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        for (Student student : studentList.getStudents()) {
            studentSaver.saveToFile(student, directoryPath);
        }
    }
}