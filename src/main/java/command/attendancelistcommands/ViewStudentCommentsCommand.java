package command.attendancelistcommands;

import attendance.AttendanceFile;
import attendance.AttendanceList;
import exception.TASyncException;
import students.Student;
import command.taskcommands.Command;

import java.util.ArrayList;

public class ViewStudentCommentsCommand implements Command<AttendanceFile> {
    //parts in format of tutname,week,studentname,matricnum
    public void execute(String parts, AttendanceFile attendanceList) {
        try {
            //if empty input string
            if (parts == null || parts.trim().isEmpty()) {
                throw TASyncException.invalidmarkAttendanceListCommand();
            }

            String[] partsArray = parts.split(",");
            //if not all inputs given or too many
            if (partsArray.length != 4) {
                throw TASyncException.invalidmarkAttendanceListCommand();
            }

            ArrayList<AttendanceList> list = attendanceList.getAttendanceList();

            AttendanceList theOne = null;

            for (AttendanceList a : list) {
                if (a.getWeekNumber() == Integer.parseInt(partsArray[1])
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


            if (theOne.getCommentList().containsKey(derStudent)) {
                ArrayList<String> comments = theOne.getCommentList().get(derStudent);

                System.out.println("List of comments: ");
                for (int i = 0; i < comments.size(); i++) {
                    System.out.println((i + 1) + ". " + comments.get(i));
                }
            } else {
                System.out.println("Selected student has no comments given to them");
            }

        } catch (TASyncException e) {
            System.out.println(e.getMessage());
        }

    }
}
