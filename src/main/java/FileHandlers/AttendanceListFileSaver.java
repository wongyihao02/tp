package FileHandlers;

import Attendance.AttendanceList;
import students.Student;
import Tutorial.TutorialClass;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class AttendanceListFileSaver implements FileSaver<AttendanceList> {
    @Override
    public void saveToFile(AttendanceList attendanceList, String directoryPath) {
        TutorialClass tutorial = attendanceList.getTutorialClass();
        String tutorialName = tutorial.getTutorialName();
        int week = attendanceList.getWeekNumber();
        String fileName = directoryPath + "/" + tutorialName + "_Week" + week + ".csv";

        try (FileWriter writer = new FileWriter(fileName)) {
            // Header
            writer.write("TutorialName,WeekNumber\n");
            writer.write(tutorialName + "," + week + "\n");

            writer.write("StudentName,MatricNumber,AttendanceStatus\n");

            for (Map.Entry<Student, String> entry : attendanceList.getAttendanceMap().entrySet()) {
                Student s = entry.getKey();
                String status = entry.getValue();
                writer.write(s.getName() + "," + s.getMatricNumber() + "," + status + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error saving attendance list: " + e.getMessage());
        }
    }
}