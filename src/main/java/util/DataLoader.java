package util;

import attendance.AttendanceFile;
import tutorial.TutorialClassList;

import java.io.File;

public class DataLoader {
    private final DataManager dataManager;

    public DataLoader(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    public TutorialClassList loadTutorialClasses() {
        TutorialClassList tutorialList = dataManager.loadTutorials();
        if (tutorialList == null || tutorialList.getTutorialClasses().isEmpty()) {
            System.out.println("Warning! No tutorials loaded or file is empty.");
            tutorialList = new TutorialClassList();
        } else {
            System.out.println("Tutorial classes loaded from: "
                    + new File(dataManager.getTutorialFilePath()).getPath() + "\n");
        }
        return tutorialList;
    }

    public AttendanceFile loadAttendanceFile(TutorialClassList tutorialList) {
        AttendanceFile attendanceFile = dataManager.loadAttendanceFiles(tutorialList);
        if (attendanceFile == null) {
            System.out.println("Warning! No attendance file loaded.");
            attendanceFile = new AttendanceFile(null);
        } else {
            System.out.println("Attendance file loaded from: "
                    + new File(dataManager.getAttendanceFilePath()).getPath() + "\n");
        }
        return attendanceFile;
    }
}
