package FileHandlers;

import Attendance.AttendanceFile;
import Attendance.AttendanceList;
import Students.Student;
import Students.StudentList;
import Tutorial.TutorialClass;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
            Map<Student, ArrayList<String>> commentMap = new HashMap<>();
            boolean startComments = false;


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
                        list.getCommentList().clear(); //replace default or previous
                        list.getAttendanceMap().putAll(attendanceMap);
                        for (Map.Entry<Student, ArrayList<String>> entry : commentMap.entrySet()) {                                 //need to add equals method for Student class
                            list.addComments(entry.getKey(), entry.getValue());
                        }

                        attendanceLists.add(list);

                        // Reset for next block
                        currentTutorial = null;
                        studentList = new ArrayList<>();
                        attendanceMap = new HashMap<>();
                        commentMap = new HashMap<>();
                        startComments = false;
                    }
                } else if (line.startsWith("#")) {
                    String[] header = line.substring(1).trim().split(",", 2);
                    currentTutorial = header[0].trim();
                    currentWeek = Integer.parseInt(header[1].trim());
                } else if (line.startsWith("//comments")) {
                    startComments = true;
                } else if (startComments) {
                    String[] parts = line.split("//", -1);
                    String name = parts[0];
                    String matric = parts[1];
                    ArrayList<String> comments = new ArrayList<>();
                    Student student = new Student(name, LocalDate.now(), "", "", matric, currentTutorial);

                    for (int i = 2 ; i < parts.length ; i++) {
                        comments.add(parts[i]);
                    }

                    commentMap.put(student, comments);
                } else {
                    String[] parts = line.split(",", -1);
                    String name = parts[0];
                    String matric = parts[1];
                    String status = parts[2];

                    Student s = new Student(name, LocalDate.now(), "", "", matric, currentTutorial);
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
                list.getCommentList().clear();
                list.getAttendanceMap().putAll(attendanceMap);
                list.getCommentList().putAll(commentMap);

                attendanceLists.add(list);
            }

        } catch (IOException e) {
            System.out.println("Error loading AttendanceFile: " + e.getMessage());
        }

        return new AttendanceFile(attendanceLists);
    }
}