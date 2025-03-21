package filehandlers;

import students.Student;
import students.StudentList;

import java.util.ArrayList;
import java.util.List;

public class StudentListFileLoader implements FileLoader<StudentList> {
    private final StudentFileLoader studentLoader = new StudentFileLoader();

    @Override
    public StudentList loadFromFile(String path) {
        throw new UnsupportedOperationException("Use loadFromLines(List<String>, tutorialName) instead.");
    }

    public StudentList loadFromLines(List<String> lines, String tutorialName) {
        ArrayList<Student> students = new ArrayList<>();
        for (String line : lines) {
            Student s = studentLoader.loadFromLine(line, tutorialName);
            if (s != null) {
                students.add(s);
            }
        }
        return new StudentList(students);
    }
}
