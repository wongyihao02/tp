import Attendance.AttendanceFile;
import Tutorial.TutorialClassList;
import Util.DataManager;

import java.io.File;

public class TASync {
    public static void main(String[] args) {
        DataManager dataManager = new DataManager();

        // Load tutorials
        TutorialClassList tutorialList = dataManager.loadTutorials();
        if (tutorialList == null || tutorialList.getTutorialClasses().isEmpty()) {
            System.out.println("No tutorials loaded or file is empty.");
            tutorialList = new TutorialClassList();
        }else {
            System.out.println("Tutorial classes loaded from: " + new File(dataManager.getTutorialFilePath()).getPath() + "\n");
        }

        AttendanceFile attendanceFile = dataManager.loadAttendanceFiles();
        if (attendanceFile == null) {
            System.out.println("No attendance file loaded.");
            attendanceFile= new AttendanceFile(null);
        }else{
            System.out.println("Tutorial classes loaded from: " + new File(dataManager.getAttendanceFilePath()).getPath() + "\n");
        }




        dataManager.saveTutorials(tutorialList);
        dataManager.saveAttendanceFile(attendanceFile);

        System.out.println("All data saved successfully!");
    }
}