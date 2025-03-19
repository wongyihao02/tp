package attendanceListCommands;

import Tutorial.TutorialClass;
import exception.TASyncException;
import taskCommands.Command;

import java.util.ArrayList;

public class createNewAttendanceList implements Command<ArrayList<ArrayList<?>>> {
    public void execute(String parts, ArrayList<ArrayList<?>> lists) {
        try {
            ArrayList<TutorialClass>
        } catch (TASyncException e) {
            System.out.println(e.getMessage());
        }
    }
}
