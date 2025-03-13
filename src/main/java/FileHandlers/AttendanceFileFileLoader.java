package FileHandlers;

import Attendance.AttendanceFile;
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

public class AttendanceFileFileLoader implements FileLoader<AttendanceFile> {
    @Override
    public AttendanceFile loadFromFile(String filePath) {
        ArrayList<AttendanceList> attendanceLists = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            String currentTutorial = null;
            int currentWeek = 0;
            ArrayList<Student> studentList = new ArrayList<>();
            HashMap<Student, String> attendanceMap = new HashMap<>();

            while ((line = reader.readLine()) != null) {
                line = line.trim();

                if (line.isEmpty()) {
                    // Finalize current block
                    if (currentTutorial != null) {
                        TutorialClass tutorial = new TutorialClass();
                        tutorial.setTutorialName(currentTutorial);
                        tutorial.setStudentList(new StudentList(studentList));
                        tutorial.setStartTime(LocalTime.of(0, 0));
                        tutorial.setEndTime(LocalTime.of(0, 0));

                        AttendanceList list = new AttendanceList(tutorial, currentWeek);
                        list.getAttendanceMap().clear(); // replace default
                        list.getAttendanceMap().putAll(attendanceMap);

                        attendanceLists.add(list);

                        // Reset for next block
                        currentTutorial = null;
                        studentList = new ArrayList<>();
                        attendanceMap = new HashMap<>();
                    }
                } else if (line.startsWith("#")) {
                    String[] header = line.substring(1).trim().split(",", 2);
                    currentTutorial = header[0].trim();
                    currentWeek = Integer.parseInt(header[1].trim());
                } else {
                    String[] parts = line.split(",", -1);
                    String name = parts[0];
                    String matric = parts[1];
                    String status = parts[2];

                    Student s = new Student(name, null, "", "", matric, currentTutorial);
                    studentList.add(s);
                    attendanceMap.put(s, status);
                }
            }

            // Final block if file doesn't end with blank line
            if (currentTutorial != null) {
                TutorialClass tutorial = new TutorialClass();
                tutorial.setTutorialName(currentTutorial);
                tutorial.setStudentList(new StudentList(studentList));
                tutorial.setStartTime(LocalTime.of(0, 0));
                tutorial.setEndTime(LocalTime.of(0, 0));

                AttendanceList list = new AttendanceList(tutorial, currentWeek);
                list.getAttendanceMap().clear();
                list.getAttendanceMap().putAll(attendanceMap);

                attendanceLists.add(list);
            }

        } catch (IOException e) {
            System.out.println("Error loading AttendanceFile: " + e.getMessage());
        }

        return new AttendanceFile(attendanceLists);
    }
}