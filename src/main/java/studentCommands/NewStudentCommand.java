package studentCommands;

import exception.TASyncException;
import student.Student;
import student.StudentList;

public class NewStudentCommand implements StudentCommand {
    @Override
    public void execute(String parts, StudentList studentList) {
        try {
            String[] studentParts = parts.split(" ");
            if (parts.length() == 6) {
                String studentName = studentParts[0].trim();
                int age = Integer.parseInt(studentParts[1].trim());
                String gender = studentParts[2].trim();
                String contact = studentParts[3].trim();
                String matricNumber = studentParts[4].trim();
                String tutorialClass = studentParts[5].trim();
                Student student = new Student(studentName, age, gender, contact, matricNumber, tutorialClass);
                studentList.addStudent(student);
            } else {
                throw TASyncException.invalidNewStudentCommand();
            }
        } catch (TASyncException e) {
            System.out.println(e.getMessage());
        }
    }
}
