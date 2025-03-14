package studentCommands;

import students.StudentList;
import task.TaskList;

public interface StudentCommand {
    void execute(String parts, StudentList studentList);
}
