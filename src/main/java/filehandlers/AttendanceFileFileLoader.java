package filehandlers;

import attendance.AttendanceFile;
import attendance.AttendanceList;
import students.Student;
import students.StudentList;
import tutorial.TutorialClass;
import tutorial.TutorialClassList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AttendanceFileFileLoader implements FileLoader<AttendanceFile> {
    private final TutorialClassList classList;

    public AttendanceFileFileLoader(TutorialClassList classList) {
        this.classList = classList;
    }

    @Override
    public AttendanceFile loadFromFile(String filePath) {
        ArrayList<AttendanceList> attendanceLists = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;

            String currentTutorial = null;
            int currentWeek = 0;
            TutorialClass currentClass = null;
            Map<Student, String> attendanceMap = new HashMap<>();
            Map<Student, ArrayList<String>> commentMap = new HashMap<>();
            boolean startComments = false;

            while ((line = reader.readLine()) != null) {
                try {
                    line = line.trim();
                    if (line.isEmpty()) {
                        continue;
                    }

                    if (line.startsWith("#")) {
                        // Start of a new AttendanceList
                        String[] header = line.substring(1).trim().split(",", 2);
                        currentTutorial = header[0].trim();
                        currentWeek = Integer.parseInt(header[1].trim());

                        currentClass = classList.getTutorialByName(currentTutorial);

                        attendanceMap = new HashMap<>();
                        commentMap = new HashMap<>();
                        startComments = false;
                    } else if (line.equalsIgnoreCase("//comments")) {
                        startComments = true;
                    } else if (line.equalsIgnoreCase("//commentEnd")) {
                        if (currentClass == null) {
                            System.err.println("Warning: Tutorial class '" + currentTutorial + "' not found. Skipping block.");
                            continue;
                        }

                        AttendanceList list = new AttendanceList(currentClass, currentWeek);
                        list.getAttendanceMap().putAll(attendanceMap);

                        for (Map.Entry<Student, ArrayList<String>> entry : commentMap.entrySet()) {
                            list.addComments(entry.getKey(), entry.getValue());
                        }

                        attendanceLists.add(list);

                        // Reset for next block
                        currentClass = null;
                        attendanceMap = new HashMap<>();
                        commentMap = new HashMap<>();
                        startComments = false;
                    } else if (startComments) {
                        // Comment line
                        String[] parts = line.split(",", 3);
                        if (parts.length < 2) {
                            continue;
                        }

                        String name = parts[0].trim();
                        String matric = parts[1].trim();
                        Student student = resolveStudent(currentClass, currentTutorial, name, matric);

                        ArrayList<String> comments = new ArrayList<>();
                        if (parts.length == 3) {
                            String[] commentArray = parts[2].split(";", -1);
                            for (String comment : commentArray) {
                                comments.add(comment.trim());
                            }
                        }

                        commentMap.put(student, comments);
                    } else {
                        // Attendance line
                        String[] parts = line.split(",", 3);
                        if (parts.length < 3) {
                            continue;
                        }

                        String name = parts[0].trim();
                        String matric = parts[1].trim();
                        String status = parts[2].trim();

                        Student student = resolveStudent(currentClass, currentTutorial, name, matric);
                        attendanceMap.put(student, status);
                    }
                } catch (Exception e) {
                    System.err.println("Warning: Skipping a block due to error: " + e.getMessage());
                    currentClass = null;
                    attendanceMap = new HashMap<>();
                    commentMap = new HashMap<>();
                    startComments = false;
                }
            }

        } catch (IOException e) {
            System.err.println("Error loading AttendanceFile: " + e.getMessage());
            return null;
        }

        return new AttendanceFile(attendanceLists);
    }

    private Student resolveStudent(TutorialClass currentClass, String tutorialName, String name, String matric) {
        if (currentClass == null) {
            return new Student(name, matric);
        }

        StudentList studentList = currentClass.getStudentList();
        if (studentList == null) {
            return new Student(name, matric);
        }

        Student student = studentList.getStudentByMatricNumber(matric);
        return (student != null) ? student : new Student(name, matric);
    }
}