package FileHandlers;

import Attendance.AttendanceList;
import students.Student;
import Tutorial.TutorialClass;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class AttendanceListFileSaver implements FileSaver<AttendanceList> {

    @Override
    public void saveToFile(AttendanceList attendanceList, String directoryPath) {
        String tutorialName = attendanceList.getTutorialClass().getTutorialName();
        int week = attendanceList.getWeekNumber();
        String fileName = directoryPath + "/" + tutorialName + "_Week" + week + ".csv";

        try (FileWriter writer = new FileWriter(fileName)) {
            saveToWriter(attendanceList, writer);
        } catch (IOException e) {
            System.out.println("Error saving attendance list: " + e.getMessage());
        }
    }

    /**
     * Reusable method to write attendance list into any FileWriter.
     */
    public void saveToWriter(AttendanceList attendanceList, FileWriter writer) throws IOException {
        TutorialClass tutorial = attendanceList.getTutorialClass();
        String tutorialName = tutorial.getTutorialName();
        int week = attendanceList.getWeekNumber();

        // Header
        writer.write("# " + tutorialName + "," + week + "\n");

        for (Map.Entry<Student, String> entry : attendanceList.getAttendanceMap().entrySet()) {
            Student s = entry.getKey();
            String status = entry.getValue();
            writer.write(s.getName() + "," + s.getMatricNumber() + "," + status + "\n");
        }

        writer.write("\n");
        writer.write("//comments\n");

        for (Map.Entry<Student, ArrayList<String>> entry : attendanceList.getCommentList().entrySet()) {
            Student s = entry.getKey();
            ArrayList<String> comments = entry.getValue();
            ArrayList<String> finalOutput = new ArrayList<>();

            writer.write(s.getName() + "," + s.getMatricNumber() + "," + comments + "\n");
        }

        writer.write("\n");
        writer.write("//commentEnd\n");

    }
}