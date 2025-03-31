package util;

import filehandlers.AttendanceFileFileLoader;
import filehandlers.AttendanceFileFileSaver;
import filehandlers.FileLoader;
import filehandlers.FileSaver;
import filehandlers.TutorialClassListFileLoader;
import filehandlers.TutorialClassListFileSaver;
import filehandlers.MarksListLoader;
import filehandlers.MarksListSaver;
import attendance.AttendanceFile;
import attendance.AttendanceList;
import tutorial.TutorialClass;
import tutorial.TutorialClassList;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class DataManager {
    private static final String DIRECTORY_PATH = "./data";
    private static final String TUTORIAL_FILE_PATH = DIRECTORY_PATH + "/AllTutorials.csv";
    private static final String ATTENDANCE_FILE_PATH = DIRECTORY_PATH + "/AttendanceFile.csv";
    private static final String MARKS_FILE_PATH = DIRECTORY_PATH + "/marks.txt";

    public TutorialClassList loadTutorials() {
        ensureFileAndDirectoryExist(TUTORIAL_FILE_PATH, DIRECTORY_PATH);
        FileLoader<TutorialClassList> tutorialLoader = new TutorialClassListFileLoader();
        return tutorialLoader.loadFromFile(TUTORIAL_FILE_PATH);
    }

    public AttendanceFile loadAttendanceFiles(TutorialClassList classList) {
        ensureFileAndDirectoryExist(ATTENDANCE_FILE_PATH, DIRECTORY_PATH);
        FileLoader<AttendanceFile> attendanceFileLoader = new AttendanceFileFileLoader(classList);
        return attendanceFileLoader.loadFromFile(ATTENDANCE_FILE_PATH);
    }

    public TutorialClassList loadMarks(TutorialClassList tutorialClassList){
        ensureFileAndDirectoryExist(MARKS_FILE_PATH, DIRECTORY_PATH);
        MarksListLoader marksLoader = new MarksListLoader();
        return marksLoader.loadMarks(tutorialClassList);
    }

    public AttendanceFile createDemoAttendanceFile(TutorialClassList tutorialList, int numberOfWeeks) {
        ArrayList<AttendanceList> attendanceLists = new ArrayList<>();
        int tutorialLimit = Math.max(2, tutorialList.getTutorialClasses().size());

        for (int i = 0; i < tutorialLimit; i++) {
            TutorialClass tutorial = tutorialList.getTutorialClasses().get(i);
            for (int week = 1; week <= numberOfWeeks; week++) {
                AttendanceList attendanceList = new AttendanceList(tutorial, week);
                if (!tutorial.getStudentList().getStudents().isEmpty()) {
                    attendanceList.markAbsent(tutorial.getStudentList().getStudents().get(0));
                }
                attendanceLists.add(attendanceList);
            }
        }
        return new AttendanceFile(attendanceLists);
    }

    public void saveTutorials(TutorialClassList tutorialList) {
        FileSaver<TutorialClassList> tutorialSaver = new TutorialClassListFileSaver();
        tutorialSaver.saveToFile(tutorialList, DIRECTORY_PATH);
    }

    public void saveAttendanceFile(AttendanceFile attendanceFile) {
        FileSaver<AttendanceFile> attendanceSaver = new AttendanceFileFileSaver();
        attendanceSaver.saveToFile(attendanceFile, DIRECTORY_PATH);
    }

    public void saveMarksList(TutorialClassList tutorialList){
        FileSaver<TutorialClassList> marksSaver = new MarksListSaver();
        marksSaver.saveToFile(tutorialList, DIRECTORY_PATH);
    }

    public String getTutorialFilePath() {
        return TUTORIAL_FILE_PATH;
    }

    public String getAttendanceFilePath() {
        return ATTENDANCE_FILE_PATH;
    }

    /**
     * Ensures the directory and file exist. If not, creates them.
     *
     * @param filePath The full path of the file to create.
     * @param directoryPath The directory to create if it doesn't exist.
     */
    private void ensureFileAndDirectoryExist(String filePath, String directoryPath) {
        File dir = new File(directoryPath);
        if (!dir.exists()) {
            boolean dirCreated = dir.mkdirs();
            if (dirCreated) {
                System.out.println("📁 Created directory: " + directoryPath);
            }
        }

        File file = new File(filePath);
        if (!file.exists()) {
            try {
                boolean fileCreated = file.createNewFile();
                if (fileCreated) {
                    System.out.println("📄 Created file: " + filePath);
                }
            } catch (IOException e) {
                System.out.println("❌ Error creating file: " + filePath);
                e.printStackTrace();
            }
        }
    }
}
