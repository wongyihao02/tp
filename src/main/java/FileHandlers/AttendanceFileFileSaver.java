package FileHandlers;

import Attendance.AttendanceFile;
import Attendance.AttendanceList;
import students.Student;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class AttendanceFileFileSaver implements FileSaver<AttendanceFile> {
    @Override
    public void saveToFile(AttendanceFile attendanceFile, String directoryPath) {
        String fileName = directoryPath + "/AttendanceFile.csv";

        try (FileWriter writer = new FileWriter(fileName)) {
            for (AttendanceList list : attendanceFile.getAttendanceList()) {
                String tutorialName = list.getTutorialClass().getTutorialName();
                int week = list.getWeekNumber();

                writer.write("# " + tutorialName + "," + week + "\n");

                for (Map.Entry<Student, String> entry : list.getAttendanceMap().entrySet()) {
                    Student s = entry.getKey();
                    String status = entry.getValue();
                    writer.write(s.getName() + "," + s.getMatricNumber() + "," + status + "\n");
                }

                writer.write("\n"); // blank line to separate blocks
            }
        } catch (IOException e) {
            System.out.println("Error saving AttendanceFile: " + e.getMessage());
        }
    }
}