package command.attendancelistcommands;

import attendance.AttendanceFile;
import attendance.AttendanceList;
import exception.TASyncException;
import students.Student;
import command.taskcommands.Command;

import java.util.ArrayList;

//@@author wongyihao02
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
            if (partsArray2.length != 2 || partsArray2[0].trim().isEmpty() || partsArray2[1].trim().isEmpty()) {
                throw TASyncException.invalidmarkAttendanceListCommand();
            }


            String[] partsArray = partsArray2[0].split(",");
            String[] commentsArray = partsArray2[1].split(";");
            for (int i = 0; i < partsArray.length; i++) {
                partsArray[i] = partsArray[i].trim();
            }
            for (int i = 0; i < commentsArray.length; i++) {
                commentsArray[i] = commentsArray[i].trim();
            }
            ArrayList<String> comments = new ArrayList<>();

            if (partsArray.length != 4) {
                throw TASyncException.invalidmarkAttendanceListCommand();
            }

            for (String comment : commentsArray) {
                comments.add(comment.trim());
            }

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

            theOne.addComments(derStudent, comments);

            System.out.println("added comment to " + partsArray[2] + " (" + partsArray[3] + ") in tutorial "
                    + partsArray[0] + " week " + partsArray[1]);
            int i = 1;
            for (String comment : comments) {
                System.out.println(i + ". " + comment);
                i += 1;
            }

            System.out.println("End of list");

        } catch (TASyncException e) {
            System.out.println(e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("second parameter has to be numbers only");
        }

    }
}
