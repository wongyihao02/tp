package FileHandlers;

import Attendance.AttendanceFile;
import Attendance.AttendanceList;
import Tutorial.TutorialClass;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import Students.Student;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

public class AttendanceFileFileLoaderTest {

    private static final String DIRECTORY_PATH = "./data";
    private static final String ATTENDANCE_FILE_PATH = DIRECTORY_PATH + "/AttendanceFile.csv";
    AttendanceList attendanceLista;
    ArrayList<String> ella;
    ArrayList<String> david;

    @BeforeEach
    void setUp() {

        FileCreator.createFileIfNotExists(ATTENDANCE_FILE_PATH, DIRECTORY_PATH);
        AttendanceFileFileLoader attendanceFileFileLoader = new AttendanceFileFileLoader();
        AttendanceFile file = attendanceFileFileLoader.loadFromFile(ATTENDANCE_FILE_PATH);
//        if (file == null) {
//            throw new RuntimeException("Attendance file could not be loaded.");
//        }
//        System.out.println("Attendance file loaded successfully.");
        ArrayList<AttendanceList> attendanceList = file.getAttendanceList();
        AttendanceList list = null;
        for (AttendanceList attendance : attendanceList) {
            TutorialClass tutorialClass = attendance.getTutorialClass();
            if (tutorialClass.getTutorialName().equals("T01") && attendance.getWeekNumber() == 1) {
                list = attendance;
                break;
            }
        }

        assert list != null;
        TutorialClass tutorialClass = list.getTutorialClass();
        Student Ella = tutorialClass.getStudentList().getStudentByName("Ella sim");
        Student David = tutorialClass.getStudentList().getStudentByName("David Ng");
        Map<Student, ArrayList<String>> comments = list.getCommentList();
        ArrayList<String> ellaComments = comments.get(Ella);
        ArrayList<String> davidComments = comments.get(David);
        ella = ellaComments;
        david = davidComments;
        attendanceLista = list;
    }

    @Test
    void testLoadNamesFromFile() {
        assertEquals(1, attendanceLista.getWeekNumber());
        assertEquals(false, attendanceLista.getCommentList().isEmpty());
    }
//    Ella sim//A005//comment1//comment2//comment3
//    David Ng//A004//comment1.2
    @Test
    void testLoadCommentsFromFile() {
       assertEquals("comment1", ella.get(0));
       assertEquals("comment2", ella.get(1));
       assertEquals("comment3", ella.get(2));
       assertEquals("comment1.2", david.get(0));
    }
}
