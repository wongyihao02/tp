package util;

import attendance.AttendanceFile;
import command.commandhandler.CommandHandler;
import command.commandhandler.CommandParser;
import students.StudentList;
import task.TaskList;
import tutorial.TutorialClassList;

import java.util.ArrayList;

public class CommandLoopHandler {
    private final UI ui;
    private final TaskList taskList;
    private final StudentList studentList;
    private final TutorialClassList tutorialList;
    private final AttendanceFile attendanceFile;

    public CommandLoopHandler(UI ui, TaskList taskList, StudentList studentList,
                              TutorialClassList tutorialList, AttendanceFile attendanceFile) {
        this.ui = ui;
        this.taskList = taskList;
        this.studentList = studentList;
        this.tutorialList = tutorialList;
        this.attendanceFile = attendanceFile;

    }

    public void runCommandLoop() {
        boolean isRunning = true;

        while (isRunning) {
            String input = ui.getUserCommand();
            assert input != null : "Error: User input should not be null";

            CommandParser commandParser = new CommandParser(input);
            String[] parts = commandParser.getParts();

            if (parts.length < 2) {
                System.out.println("Invalid command format. Please use: add -[type] [task details]");
                break;
            }

            String command = parts[0].toUpperCase();
            String listType = parts[1];

            CommandHandler commandHandler;

            ArrayList<Object> tutAtten = new ArrayList<>();
            tutAtten.add(tutorialList);
            tutAtten.add(attendanceFile);

            if ("ADD".equals(command) || "HELP".equals(command) || listType.equalsIgnoreCase("-p")) {
                commandHandler = new CommandHandler(taskList, parts);
            } else if ("BYE".equals(command)) {
                commandHandler = new CommandHandler(taskList, parts);
            } else if (listType.equalsIgnoreCase("-s")) {
                commandHandler = new CommandHandler(studentList, parts);
            } else if (listType.equalsIgnoreCase("-t") || listType.equalsIgnoreCase("-m")) {
                commandHandler = new CommandHandler(tutorialList, parts);
            } else if (listType.equalsIgnoreCase("-a")) {
                commandHandler = new CommandHandler(attendanceFile, parts);
            } else if (listType.equalsIgnoreCase("-at")) {
                commandHandler = new CommandHandler(tutAtten, parts);
            } else {
                commandHandler = new CommandHandler(null, parts);
                System.out.println("Invalid command");
            }

            isRunning = commandHandler.runCommand();
        }

    }
}
