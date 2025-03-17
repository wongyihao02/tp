import Attendance.AttendanceFile;
import Attendance.AttendanceList;
import FileHandlers.*;
import students.Student;
import Tutorial.TutorialClass;
import Tutorial.TutorialClassList;

import java.io.File;
import java.util.ArrayList;

public class TASync {
    private static final String DIRECTORY_PATH = "./data";
    private static final String TUTORIAL_FILE_PATH = DIRECTORY_PATH + "/AllTutorials.csv";
    private static final String ATTENDANCE_FILE_PATH = DIRECTORY_PATH + "/AttendanceFile.csv";

    public static void main(String[] args) {
        // Ensure both files and directory exist
        FileCreator.createFileIfNotExists(TUTORIAL_FILE_PATH, DIRECTORY_PATH);
        FileCreator.createFileIfNotExists(ATTENDANCE_FILE_PATH, DIRECTORY_PATH);

        // Load tutorials
        FileLoader<TutorialClassList> tutorialLoader = new TutorialClassListFileLoader();
        TutorialClassList tutorialList = tutorialLoader.loadFromFile(TUTORIAL_FILE_PATH);

        if (tutorialList == null || tutorialList.getTutorialClasses().isEmpty()) {
            System.out.println("No tutorials loaded or file is empty.");
            return;
        }

        System.out.println("✅ Tutorial classes loaded from: " + new File(TUTORIAL_FILE_PATH).getPath() + "\n");

        for (TutorialClass tutorial : tutorialList.getTutorialClasses()) {
            System.out.println("Tutorial: " + tutorial.getTutorialName());
            System.out.println("Time: " + tutorial.getStartTime() + " - " + tutorial.getEndTime());
            System.out.println("Students:");
            for (Student s : tutorial.getStudentList().getStudents()) {
                System.out.println(" - " + s.getName() + " (" + s.getMatricNumber() + ")");
            }
            System.out.println();
        }

        // Create a few AttendanceLists (for Week 1 and 2 for the first two tutorial classes)
        ArrayList<AttendanceList> attendanceLists = new ArrayList<>();
        int numberOfWeeks = 6;
        int tutorialLimit = Math.max(2, tutorialList.getTutorialClasses().size());

        for (int i = 0; i < tutorialLimit; i++) {
            TutorialClass tutorial = tutorialList.getTutorialClasses().get(i);
            for (int week = 1; week <= numberOfWeeks; week++) {
                AttendanceList attendanceList = new AttendanceList(tutorial, week);
                // For demo: Mark first student as Absent
                if (!tutorial.getStudentList().getStudents().isEmpty()) {
                    attendanceList.markAbsent(tutorial.getStudentList().getStudents().get(0));
                }
                attendanceLists.add(attendanceList);
            }
        }

        AttendanceFile attendanceFile = new AttendanceFile(attendanceLists);

        // Save AllTutorials.csv
        FileSaver<TutorialClassList> tutorialSaver = new TutorialClassListFileSaver();
        tutorialSaver.saveToFile(tutorialList, DIRECTORY_PATH);

        // Save AttendanceFile.csv
        FileSaver<AttendanceFile> attendanceSaver = new AttendanceFileFileSaver();
        attendanceSaver.saveToFile(attendanceFile, DIRECTORY_PATH);

        System.out.println("✅ All data saved successfully!");
    }
}
