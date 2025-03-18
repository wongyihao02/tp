import Attendance.AttendanceFile;
import students.Student;
import Tutorial.TutorialClass;
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
            return;
        }

        System.out.println("Tutorial classes loaded from: " + new File(dataManager.getTutorialFilePath()).getPath() + "\n");

        for (TutorialClass tutorial : tutorialList.getTutorialClasses()) {
            System.out.println("Tutorial: " + tutorial.getTutorialName());
            System.out.println("Time: " + tutorial.getStartTime() + " - " + tutorial.getEndTime());
            System.out.println("Students:");
            for (Student s : tutorial.getStudentList().getStudents()) {
                System.out.println(" - " + s.getName() + " (" + s.getMatricNumber() + ")");
            }
            System.out.println();
        }

        // Generate attendance for demo
        AttendanceFile attendanceFile = dataManager.createDemoAttendanceFile(tutorialList, 6);

        // Save everything
        dataManager.saveTutorials(tutorialList);
        dataManager.saveAttendanceFile(attendanceFile);

        System.out.println("All data saved successfully!");
    }
}