package FileHandlers;

import Students.Student;
import Util.DateTimeFormatterUtil;

import java.time.LocalDate;

public class StudentFileLoader implements FileLoader<Student> {

    @Override
    public Student loadFromFile(String filePath) {
        throw new UnsupportedOperationException("Use loadFromLine(String line) instead for unified file.");
    }

    public Student loadFromLine(String line, String tutorialName) {
        String[] parts = line.split(",", -1);
        String name = parts[0];
        LocalDate dob = DateTimeFormatterUtil.parseDate(parts[1]);
        String gender = parts[2];
        String contact = parts[3];
        String matric = parts[4];
        String remark = parts.length > 5 ? parts[5] : "";

        Student student = new Student(name, dob, gender, contact, matric, tutorialName);
        student.setRemark(remark);
        return student;
    }
}