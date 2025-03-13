package FileHandlers;

import Attendance.AttendanceList;
import Students.Student;
import Students.StudentList;
import Tutorial.TutorialClass;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AttendanceListFileLoader implements FileLoader<AttendanceList> {
    @Override
    public AttendanceList loadFromFile(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            // Skip header line
            reader.readLine(); // "TutorialName,WeekNumber"
            String metadata = reader.readLine(); // e.g., "T01,1"
            String[] metaParts = metadata.split(",", -1);
            String tutorialName = metaParts[0];
            int weekNumber = Integer.parseInt(metaParts[1]);

            reader.readLine(); // "StudentName,MatricNumber,AttendanceStatus"

            ArrayList<Student> students = new ArrayList<>();
            Map<Student, String> attendanceMap = new HashMap<>();

            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",", -1);
                String name = parts[0];
                String matricNumber = parts[1];
                String status = parts[2];

                // Create a minimal student object
                Student student = new Student(name, null, "", "", matricNumber, tutorialName);
                students.add(student);
                attendanceMap.put(student, status);
            }

            // Assemble the tutorial and attendance list
            TutorialClass tutorial = new TutorialClass();
            tutorial.setTutorialName(tutorialName);
            tutorial.setStudentList(new StudentList(students));
            tutorial.setStartTime(LocalTime.of(0, 0)); // default if not stored
            tutorial.setEndTime(LocalTime.of(0, 0));

            AttendanceList attendanceList = new AttendanceList(tutorial, weekNumber);

            // Replace default map with loaded one
            for (Map.Entry<Student, String> entry : attendanceMap.entrySet()) {
                attendanceList.getAttendanceMap().put(entry.getKey(), entry.getValue());
            }

            return attendanceList;

        } catch (IOException e) {
            System.out.println("Error loading attendance list: " + e.getMessage());
        }

        return null;
    }
}