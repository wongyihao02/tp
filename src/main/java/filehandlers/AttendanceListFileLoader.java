package filehandlers;

import attendance.AttendanceList;
import students.Student;
import students.StudentList;
import tutorial.TutorialClass;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

//@@author xubin0
public class AttendanceListFileLoader implements FileLoader<AttendanceList> {
    @Override
    public AttendanceList loadFromFile(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            // Read header line: # T01,1
            String metadata = reader.readLine();
            if (metadata == null || !metadata.startsWith("#")) {
                throw new IOException("Invalid file format: missing metadata line.");
            }

            String[] metaParts = metadata.substring(1).trim().split(",", -1);
            String tutorialName = metaParts[0].trim();
            int weekNumber = Integer.parseInt(metaParts[1].trim());

            ArrayList<Student> students = new ArrayList<>();
            Map<Student, String> attendanceMap = new HashMap<>();
            Map<Student, ArrayList<String>> commentMap = new HashMap<>();

            boolean readingComments = false;

            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();

                if (line.isEmpty()) {
                    continue;
                }

                if (line.equalsIgnoreCase("//comments")) {
                    readingComments = true;
                    continue;
                }

                if (line.equalsIgnoreCase("//commentEnd")) {
                    readingComments = false;
                    break; // assuming nothing else after comment section
                }

                if (readingComments) {
                    String[] parts = line.split(",", -1);
                    String name = parts[0].trim();
                    String matric = parts[1].trim();
                    Student student = new Student(name, LocalDate.now(), "", "", matric);
                    ArrayList<String> comments = new ArrayList<>();

                    for (int i = 2; i < parts.length; i++) {
                        comments.add(parts[i].trim());
                    }

                    commentMap.put(student, comments);
                } else {
                    String[] parts = line.split(",", -1);
                    String name = parts[0].trim();
                    String matric = parts[1].trim();
                    String status = parts[2].trim();

                    Student student = new Student(name, LocalDate.now(), "", "", matric);
                    students.add(student);
                    attendanceMap.put(student, status);
                }
            }

            // Assemble TutorialClass
            TutorialClass tutorial = new TutorialClass();
            tutorial.setTutorialName(tutorialName);
            tutorial.setStudentList(new StudentList(students));
            tutorial.setStartTime(LocalTime.of(0, 0));
            tutorial.setEndTime(LocalTime.of(0, 0));

            // Create AttendanceList
            AttendanceList attendanceList = new AttendanceList(tutorial, weekNumber);

            for (Map.Entry<Student, String> entry : attendanceMap.entrySet()) {
                attendanceList.getAttendanceMap().put(entry.getKey(), entry.getValue());
            }

            for (Map.Entry<Student, ArrayList<String>> entry : commentMap.entrySet()) {
                attendanceList.addComments(entry.getKey(), entry.getValue());
            }

            return attendanceList;

        } catch (IOException e) {
            System.out.println("Error loading attendance list: " + e.getMessage());
        }

        return null;
    }
}
