package Command.attendanceListCommands;

import Attendance.AttendanceFile;
import Attendance.AttendanceList;
import Tutorial.TutorialClass;
import Tutorial.TutorialClassList;
import exception.TASyncException;
import students.Student;
import Command.taskCommands.Command;

import java.util.ArrayList;

public class createNewAttendanceList implements Command<ArrayList<Object>> {

    //parts in tutname,week num
    public void execute(String parts, ArrayList<Object> tutAtten) {
        try {
            TutorialClassList tutlist = (TutorialClassList) tutAtten.get(0);
            AttendanceFile attendanceFile = (AttendanceFile) tutAtten.get(1);

            if (parts == null || parts.trim().isEmpty()) {
                throw TASyncException.invalidListAttendanceListCommand();
            }
            String[] partsArray = parts.split(",");
            if (partsArray.length != 2) {
                throw TASyncException.invalidListAttendanceListCommand();
            }

            TutorialClass tutClass = tutlist.getByName(partsArray[0]);

            if (tutClass == null) {
                throw TASyncException.invalidListAttendanceListCommand();
            }
            AttendanceList attenlist = attendanceFile.getAttendanceByNameAndWeek(Integer.parseInt(partsArray[1]), tutClass.getTutorialName());

            if (attenlist == null) {
                attenlist = new AttendanceList(tutClass, Integer.parseInt(partsArray[1]));
                for (Student student : attenlist.getTutorialClass().getStudentList().getStudents()) {
                    attenlist.markAbsent(student);
                }

                attendanceFile.addAttendanceList(attenlist);
                System.out.println("Attendance List created");
            } else {
                System.out.println("Attendance list for the week already exists");
            }
        } catch (TASyncException e) {
            System.out.println(e.getMessage());
        }
    }
}
