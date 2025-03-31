import login.LoginHandler;
import login.TALogin;
import attendance.AttendanceFile;
import tutorial.TutorialClassList;
import util.CommandLoopHandler;
import util.DataLoader;
import util.DataManager;
import util.UI;
import students.StudentList;
import task.TaskList;

public class TASync {
    public static void main(String[] args) {
        DataManager dataManager = new DataManager();
        DataLoader dataLoader = new DataLoader(dataManager);
        UI ui = new UI();
        boolean isRunning;

        ui.printLogin();
        TALogin passwordHolder = dataManager.loadPassword();
        LoginHandler loginHandler = new LoginHandler(ui, passwordHolder);
        isRunning=loginHandler.handleLogin();

        if(isRunning) {

            TutorialClassList tutorialList = dataLoader.loadTutorialClasses();
            AttendanceFile attendanceFile = dataLoader.loadAttendanceFile(tutorialList);
            TaskList taskList = new TaskList();
            StudentList studentlist = new StudentList();
            /*
            for (AttendanceList attendanceList : attendanceFile.getAttendanceList()) {
                System.out.println(attendanceList);
            } // just to check if attendanceFile imported correctly
            */


            assert tutorialList != null : "Error: tutorialList should not be null";
            assert attendanceFile != null : "Error: attendanceFile should not be null";

            ui.printWelcome();
            ui.displayDailySchedule(taskList, tutorialList);

            CommandLoopHandler loopHandler = new CommandLoopHandler(ui, taskList, studentlist,
                    tutorialList, attendanceFile, passwordHolder);
            loopHandler.runCommandLoop();


            ui.printGoodbye();
            ui.close();
            dataManager.saveTutorials(tutorialList);
            dataManager.saveAttendanceFile(attendanceFile);
            dataManager.savePassword(passwordHolder);

            System.out.println("All data saved successfully!");
        }
    }
}
