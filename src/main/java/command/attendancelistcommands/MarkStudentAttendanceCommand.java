package command.attendancelistcommands;

import attendance.AttendanceFile;
import attendance.AttendanceList;
import exception.TASyncException;
import students.Student;
import command.taskcommands.Command;

import java.util.ArrayList;
import java.util.Map;

//@@author wongyihao02
public class MarkStudentAttendanceCommand implements Command<AttendanceFile> {

    //parts in form of tutname,weeknum,name,matricnumber
    public void execute(String parts, AttendanceFile attendanceList) {
        try {
            //if empty input string
            if (parts == null || parts.trim().isEmpty()) {
                throw TASyncException.invalidmarkAttendanceListCommand();
            }

            String[] partsArray = parts.split(",");

            for (int i = 0; i < partsArray.length; i++) {
                partsArray[i] = partsArray[i].trim();
            }
            //if not all inputs given or too many
            if (partsArray.length != 4) {
                throw TASyncException.invalidmarkAttendanceListCommand();
            }

            assert partsArray.length == 4 : "Only 4 inputs should be passed";

            ArrayList<AttendanceList> list = attendanceList.getAttendanceList();

            AttendanceList theOne = null;

            for (AttendanceList a : list) {
                if (a.getWeekNumber() == Integer.parseInt(partsArray[1].trim())
                        && a.getTutorialClass().getTutorialName().equalsIgnoreCase(partsArray[0])) {
                    theOne = a;
                }
            }
            //if given tut and week num has no attendanceList
            if (theOne == null) {
                throw TASyncException.invalidmarkAttendanceListCommand();
            }

            ArrayList<Student> stdList = theOne.getTutorialClass().getStudentList().getStudents();
            Student derStudent = null;
            for (Student s : stdList) {
                if (s.getMatricNumber().equalsIgnoreCase(partsArray[3])
                        && s.getName().equalsIgnoreCase(partsArray[2])) {
                    derStudent = s;
                }
            }

            //if name and matric num does not match a student in the attendanceList
            if (derStudent == null) {
                throw TASyncException.invalidmarkAttendanceListCommand();
            }

            assert derStudent != null : "by now shouldn't be null";
            assert theOne != null : "by now shouldn't be null";

            Map<Student, String> attendanceMap = theOne.getAttendanceMap();
            attendanceMap.replace(derStudent, "Present");
            System.out.println("Student marked Present");
        } catch (TASyncException e) {
            System.out.println(e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("second parameter has to be numbers only");
        }

    }
}
