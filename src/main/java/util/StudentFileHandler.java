package util;

import student.Student;
import student.StudentList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class StudentFileHandler {

    private static final String FILE_PATH = "./tpData/StudentList.txt";

    public static void saveStudents(StudentList students) {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            ArrayList<Student> studentList = new ArrayList<>(students.getStudents());
            for (int i = 0; i < studentList.toArray().length; i++) {
                writer.write(studentList.get(i).toFileFormat() + "\n");
            }
            System.out.println("Tasks saved successfully!");
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }


    public static StudentList loadStudents() {
        ArrayList<Student> students = new ArrayList<>();
        File f = new File(FILE_PATH);
        try {
            Scanner s = new Scanner(f);

            while (s.hasNext()) {
                String line = s.nextLine();
                String[] parts = line.split(",");
                if (parts.length < 5) continue; // Ignore malformed lines

                String name = parts[0];
                int age = Integer.parseInt(parts[1]);
                String gender = parts[2];
                String contact = parts[3];
                String matricNumber = parts[4];
                String tutorialClass = parts[5];

                students.add(new Student(name, age, gender, contact, matricNumber, tutorialClass));
            }
            return new StudentList(students);
        } catch (FileNotFoundException e) {
            System.out.println("no existing data file found, new file will be created!");
            return new StudentList();
        }
    }
}
