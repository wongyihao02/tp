package command.attendancelistcommands;

import attendance.AttendanceFile;
import attendance.AttendanceList;
import exception.TASyncException;
import students.Student;
import command.taskcommands.Command;

import java.util.ArrayList;
import java.util.Map;

//@@author wongyihao02
public class ShowAttendanceListCommand implements Command<AttendanceFile> {

    //parts in format of tutname,week
    public void execute(String parts, AttendanceFile attendanceList) {

        try {
            //if empty input string
            if (parts == null || parts.trim().isEmpty()) {
                throw TASyncException.invalidListAttendanceListCommand();
            }

            String[] partsArray = parts.split(",");
            //if not all inputs given or too many
            if (partsArray.length != 2) {
                throw TASyncException.invalidListAttendanceListCommand();
            }

            ArrayList<AttendanceList> list = attendanceList.getAttendanceList();
            AttendanceList theOne = null;

            int weekNumber = Integer.parseInt(partsArray[1]);
            String tutorialName = partsArray[0];

            for (AttendanceList a : list) {
                if (a.getWeekNumber() == weekNumber
                        && a.getTutorialClass().getTutorialName().equalsIgnoreCase(tutorialName)) {
                    theOne = a;
                }
            }
            //if given tut and week num has no attendanceList
            if (theOne == null) {
                throw TASyncException.invalidListAttendanceListCommand();
            }

            Map<Student, String> attendanceMap = theOne.getAttendanceMap();

            System.out.println(
                    "Attendance List for tutorial "
                            + theOne.getTutorialClass().getTutorialName()
                            + " week "
                            + theOne.getWeekNumber()
                            + ":"
            );

            if (attendanceMap.isEmpty()) {
                System.out.println("No student in attendance list");
            } else {
                for (Map.Entry<Student, String> entry : attendanceMap.entrySet()) {
                    Student student = entry.getKey();
                    String attendance = entry.getValue();

                    System.out.println(student.getName() + "(" + student.getMatricNumber() + "): " + attendance);
                }
                System.out.println("end of list");
            }

        } catch (TASyncException e) {
            System.out.println(e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("second parameter has to be numbers only");
        }

    }
}
