package command.attendancelistcommands;

import attendance.AttendanceFile;
import attendance.AttendanceList;
import exception.TASyncException;
import students.Student;
import command.taskcommands.Command;

import java.util.ArrayList;

public class CommentOnStudentCommand implements Command<AttendanceFile> {

    //parts in format of tutname,week,studentname,matricnum//comments1;comments2              (comments seperated by ;)
    public void execute(String parts, AttendanceFile attendanceList) {
        try {
            //if empty input string
            if (parts == null || parts.trim().isEmpty()) {
                throw TASyncException.invalidmarkAttendanceListCommand();
            }

            String[] partsArray2 = parts.split("//");
            //if not all inputs given or too many
            if (partsArray2.length != 2) {
                throw TASyncException.invalidmarkAttendanceListCommand();
            }

            String[] partsArray = partsArray2[0].split(",");
            String[] commentsArray = partsArray2[1].split(";");
            ArrayList<String> comments = new ArrayList<>();

            for (String comment : commentsArray) {
                comments.add(comment.trim());
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

            theOne.addComments(derStudent, comments);

        } catch (TASyncException e) {
            System.out.println(e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("second parameter has to be numbers only");
        }

    }
}
