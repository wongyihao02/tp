package Attendance;

import java.util.ArrayList;

public class AttendanceFile {
    ArrayList<AttendanceList> attendanceFile;

    public AttendanceFile(ArrayList<AttendanceList> attendanceList) {
        attendanceFile = attendanceList;
    }
    public ArrayList<AttendanceList> getAttendanceList() {
        return attendanceFile;
    }

    public AttendanceList getAttendanceByNameAndWeek(int week, String tutorialName) {
        for (AttendanceList list : attendanceFile) {
            boolean equalWeekNumber= list.getWeekNumber() == week;
            boolean equalTutorialName=list.getTutorialClass().getTutorialName().equals(tutorialName);
            if ( equalWeekNumber&& equalTutorialName) {
                return list;
            }
        }
        System.out.println("Attendance file does not contain this attendance list");
        return null;
    }
}
