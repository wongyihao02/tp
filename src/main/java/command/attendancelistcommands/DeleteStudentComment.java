package command.attendancelistcommands;

import attendance.AttendanceFile;
import attendance.AttendanceList;
import exception.TASyncException;
import students.Student;
import command.taskcommands.Command;

import java.util.ArrayList;
import java.util.Map;

public class DeleteStudentComment implements Command<AttendanceFile> {
    //parts in format of tutname,week,studentname,matricnum//commentnum
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

            String[] partsArray = partsArray2[0].split(" ");

            ArrayList<AttendanceList> list = attendanceList.getAttendanceList();

            AttendanceList theOne = null;

            for (AttendanceList a : list) {
                if (a.getWeekNumber() == Integer.parseInt(partsArray[1])
                        && a.getTutorialClass().getTutorialName().equals(partsArray[0])) {
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

            Map<Student, ArrayList<String>> commentlist = theOne.getCommentList();

            if (commentlist.containsKey(derStudent)) {
                int index = Integer.parseInt(partsArray[1]) - 1;
                int size = commentlist.get(derStudent).size();

                if (index >= 0 && index < size) {
                    commentlist.get(derStudent).remove(index);
                    System.out.println("Comment deleted");
                } else {
                    System.out.println("Comment to be deleted was not present");
                }
            } else {
                System.out.println("Student has no comments");
            }

        } catch (TASyncException e) {
            System.out.println(e.getMessage());
        }

    }

}
