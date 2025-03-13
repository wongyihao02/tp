package student;

import java.util.ArrayList;
import student.Student;

public class StudentList {
    private ArrayList<Student> students;

    public StudentList() {
        this.students = new ArrayList<>();
    }

    public StudentList(ArrayList<Student> students) { //used when loading existing student list
        this.students = students;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public void setStudents(ArrayList<Student> students) {
        this.students = students;
    }
    public void addStudent(Student student) {
        this.students.add(student);
    }
    public void removeStudent(Student student) {
        this.students.remove(student);
    }
}
