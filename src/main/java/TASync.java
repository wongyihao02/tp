import attendance.AttendanceFile;
import tutorial.TutorialClassList;
import util.DataManager;
import util.UI;
import command.commandhandler.CommandHandler;
import command.commandhandler.CommandParser;
import students.StudentList;
import task.TaskList;

import java.io.File;
import java.util.ArrayList;

public class TASync {
    public static void main(String[] args) {
        DataManager dataManager = new DataManager();

        // Load tutorials
        TutorialClassList tutorialList = dataManager.loadTutorials();
        if (tutorialList == null || tutorialList.getTutorialClasses().isEmpty()) {
            System.out.println("No tutorials loaded or file is empty.");
            tutorialList = new TutorialClassList();
        }else {
            System.out.println("Tutorial classes loaded from: "
                    + new File(dataManager.getTutorialFilePath()).getPath() + "\n");
        }

        AttendanceFile attendanceFile = dataManager.loadAttendanceFiles(tutorialList);
        if (attendanceFile == null) {
            System.out.println("No attendance file loaded.");
            attendanceFile= new AttendanceFile(null);
        }else{
            System.out.println("Tutorial classes loaded from: "
                    + new File(dataManager.getAttendanceFilePath()).getPath() + "\n");
        }

        /*
        for (AttendanceList attendanceList : attendanceFile.getAttendanceList()) {
            System.out.println(attendanceList);
        } // just to check if attendanceFile imported correctly
        */

        TaskList taskList = new TaskList();
        StudentList studentlist = new StudentList();
        UI ui = new UI();

        assert tutorialList != null : "Error: tutorialList should not be null";
        assert attendanceFile != null : "Error: attendanceFile should not be null";
        assert ui != null : "Error: UI should not be null";

        ui.printWelcome();
        ui.displayDailySchedule(taskList, tutorialList);
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
            ArrayList<Object> tutAtten = new ArrayList<>();
            tutAtten.add(tutorialList);
            tutAtten.add(attendanceFile);
            String listType = parts[1];
            String command = parts[0].toUpperCase();
            CommandHandler commandHandler;
            if ("ADD".equals(command) || "HELP".equals(command) || listType.equalsIgnoreCase("-p")) {
                commandHandler = new CommandHandler(taskList, parts);
            } else if ("BYE".equals(command)) {
                commandHandler = new CommandHandler(taskList, parts);
            } else if (listType.equalsIgnoreCase("-s")) {
                commandHandler = new CommandHandler(studentlist, parts);
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

        ui.printGoodbye();
        ui.close();
        dataManager.saveTutorials(tutorialList);
        dataManager.saveAttendanceFile(attendanceFile);

        System.out.println("All data saved successfully!");
    }
}
