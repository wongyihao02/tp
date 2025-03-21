package filehandlers;

import attendance.AttendanceFile;
import attendance.AttendanceList;

import java.io.FileWriter;
import java.io.IOException;

public class AttendanceFileFileSaver implements FileSaver<AttendanceFile> {
    private final AttendanceListFileSaver listSaver = new AttendanceListFileSaver();

    @Override
    public void saveToFile(AttendanceFile attendanceFile, String directoryPath) {
        String fileName = directoryPath + "/AttendanceFile.csv";

        try (FileWriter writer = new FileWriter(fileName)) {
            for (AttendanceList list : attendanceFile.getAttendanceList()) {
                listSaver.saveToWriter(list, writer);
            }
        } catch (IOException e) {
            System.out.println("Error saving AttendanceFile: " + e.getMessage());
        }
    }
}
