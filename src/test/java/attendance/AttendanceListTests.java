package attendance;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import filehandlers.AttendanceFileFileLoader;
import filehandlers.TutorialClassListFileLoader;
import students.Student;
import students.StudentList;
import tutorial.TutorialClass;
import tutorial.TutorialClassList;


public class AttendanceListTests {

    private AttendanceFile attendanceFileForTest;
    private TutorialClassList tutorialClassListForTest;
    private Path tempFileGlobal;
    private Path tempFile2Global;

    @BeforeEach
    void setup() throws IOException {
        String attendancefiletext = """
                # T01,1
                Alice Tan,A001,Absent
                Harry Choo,A008,Present
                Farhan Lee,A006,Present
                Grace Tan,A007,Present
                David Ng,A004,Present
                Ella Sim,A005,Present
                Ben Lim,A002,Present
                Chloe Ong,A003,Present
                
                //comments
                Alice Tan,A001,Absent without valid reason
                Harry Choo,A008,Participated actively
                Grace Tan,A007,Asked thoughtful questions
                //commentEnd
                
                # T01,2
                Alice Tan,A001,Absent
                Harry Choo,A008,Present
                Farhan Lee,A006,Present
                Grace Tan,A007,Present
                David Ng,A004,Present
                Ella Sim,A005,Present
                Ben Lim,A002,Present
                Chloe Ong,A003,Present
                
                //comments
                David Ng,A004,Consistent participation
                Chloe Ong,A003,Submitted work early
                //commentEnd
                
                # T01,3
                Alice Tan,A001,Absent
                Harry Choo,A008,Present
                Farhan Lee,A006,Present
                Grace Tan,A007,Present
                David Ng,A004,Present
                Ella Sim,A005,Present
                Ben Lim,A002,Present
                Chloe Ong,A003,Present
                
                //comments
                Harry Choo,A008,Helped peers
                Ella Sim,A005,Good engagement in class
                //commentEnd
                
                # T01,4
                Alice Tan,A001,Absent
                Harry Choo,A008,Present
                Farhan Lee,A006,Present
                Grace Tan,A007,Present
                David Ng,A004,Present
                Ella Sim,A005,Present
                Ben Lim,A002,Present
                Chloe Ong,A003,Present
                
                //comments
                Farhan Lee,A006,Excellent presentation
                Ben Lim,A002,Active in group discussion
                //commentEnd
                
                # T02,1
                Kenny Yeo,A011,Present
                Lena Khoo,A012,Present
                Jasmine Goh,A010,Present
                Isaac Tan,A009,Absent
                Owen Teo,A015,Present
                Phoebe Ong,A016,Present
                Marcus Chin,A013,Present
                Nicole Tay,A014,Present
                
                //comments
                Lena Khoo,A012,Shared helpful resources
                Isaac Tan,A009,Absent without informing
                //commentEnd
                
                # T02,2
                Kenny Yeo,A011,Present
                Lena Khoo,A012,Present
                Jasmine Goh,A010,Present
                Isaac Tan,A009,Absent
                Owen Teo,A015,Present
                Phoebe Ong,A016,Present
                Marcus Chin,A013,Present
                Nicole Tay,A014,Present
                
                //comments
                Phoebe Ong,A016,Excellent teamwork
                Nicole Tay,A014,Consistent contributor
                //commentEnd
                
                # T02,3
                Kenny Yeo,A011,Present
                Lena Khoo,A012,Present
                Jasmine Goh,A010,Present
                Isaac Tan,A009,Absent
                Owen Teo,A015,Present
                Phoebe Ong,A016,Present
                Marcus Chin,A013,Present
                Nicole Tay,A014,Present
                
                //comments
                Kenny Yeo,A011,Assisted with setup
                Jasmine Goh,A010,Very attentive
                //commentEnd
                """;

        String tutorials = """
                # T01,MONDAY,09:00,10:30
                Alice Tan,13/03/2002,Female,81234567,A001,Class Rep
                Ben Lim,21/04/2002,Male,81234568,A002,
                Chloe Ong,30/05/2002,Female,81234569,A003,
                David Ng,17/06/2002,Male,81234570,A004,
                Ella Sim,25/07/2002,Female,81234571,A005,
                Farhan Lee,10/08/2002,Male,81234572,A006,
                Grace Tan,18/09/2002,Female,81234573,A007,
                Harry Choo,05/10/2002,Male,81234574,A008,
                
                # T02,TUESDAY,11:00,12:30
                Isaac Tan,13/03/2001,Male,81234575,A009,
                Jasmine Goh,21/04/2001,Female,81234576,A010,
                Kenny Yeo,30/05/2001,Male,81234577,A011,
                Lena Khoo,17/06/2001,Female,81234578,A012,
                Marcus Chin,25/07/2001,Male,81234579,A013,
                Nicole Tay,10/08/2001,Female,81234580,A014,
                Owen Teo,18/09/2001,Male,81234581,A015,
                Phoebe Ong,05/10/2001,Female,81234582,A016,""";

        // Step 2: Write to a temporary file
        Path tempFile = Files.createTempFile("tutorial_classes", ".txt");
        Files.writeString(tempFile, tutorials);
        tempFileGlobal = tempFile;

        Path tempFile2 = Files.createTempFile("attendanceFile", ".txt");
        Files.writeString(tempFile2, attendancefiletext);
        tempFile2Global = tempFile2;

        // Step 3: Load using your file loader
        TutorialClassListFileLoader loader = new TutorialClassListFileLoader();
        TutorialClassList tutorialList = loader.loadFromFile(tempFile.toString());

        AttendanceFileFileLoader loader2 = new AttendanceFileFileLoader(tutorialList);
        AttendanceFile attendanceFile = loader2.loadFromFile(tempFile2.toString());
        ArrayList<AttendanceList> attendanceFileList = attendanceFile.getAttendanceList();
        attendanceFileForTest = attendanceFile;
        tutorialClassListForTest = tutorialList;
    }

    @AfterEach
    public void deleteTempFiles() throws IOException {
        Files.deleteIfExists(tempFileGlobal);
        Files.deleteIfExists(tempFile2Global);
    }

    @Test
    public void testAttendance() {
        ArrayList<AttendanceList> attendanceFileList = attendanceFileForTest.getAttendanceList();
        AttendanceList attendancelist = attendanceFileForTest.getAttendanceByNameAndWeek(2, "T02");
        TutorialClass tutorial = attendancelist.getTutorialClass();
        StudentList listOfStudents = tutorial.getStudentList();
        Student phoebeOng = listOfStudents.getStudentByName("Phoebe Ong");
        assertEquals("Phoebe Ong", phoebeOng.getName());
        assertTrue(attendancelist.isPresent(phoebeOng));
        attendancelist.markAbsent(phoebeOng);
        assertFalse(attendancelist.isPresent(phoebeOng));
    }

    @Test
    public void testComments() {
        ArrayList<AttendanceList> attendanceFileList = attendanceFileForTest.getAttendanceList();
        AttendanceList attendancelist = attendanceFileForTest.getAttendanceByNameAndWeek(2, "T02");
        TutorialClass tutorial = attendancelist.getTutorialClass();
        StudentList listOfStudents = tutorial.getStudentList();
        Student phoebeOng = listOfStudents.getStudentByName("Phoebe Ong");
        Student Kenny = listOfStudents.getStudentByName("Kenny Yeo");
        assertEquals("Phoebe Ong", phoebeOng.getName());
        ArrayList<String> comments = attendancelist.getComment(phoebeOng);

        assertEquals("Excellent teamwork", comments.get(0));
        ArrayList<String> comments2 = new ArrayList<>();
        comments2.add("attentive in class");
        comments2.add("submits work on time");
        attendancelist.addComments(phoebeOng, comments2);
        comments = attendancelist.getComment(phoebeOng);
        assertEquals("Excellent teamwork", comments.get(0));
        assertEquals("attentive in class", comments.get(1));
        assertEquals("submits work on time", comments.get(2));
    }

    @Test
    public void testWeekNum() {
        ArrayList<AttendanceList> attendanceFileList = attendanceFileForTest.getAttendanceList();
        AttendanceList attendancelist = attendanceFileForTest.getAttendanceByNameAndWeek(2, "T02");
        TutorialClass tutorial = attendancelist.getTutorialClass();
        assertEquals(2, attendancelist.getWeekNumber());
        assertEquals("T02", tutorial.getTutorialName());
        attendancelist.setWeekNumber(1);
        assertEquals(1, attendancelist.getWeekNumber());

    }

    @Test
    public void testSetTutorialClass() {
        TutorialClass tutorial2 = tutorialClassListForTest.getTutorialByName("T01");
        AttendanceList attendancelist = attendanceFileForTest.getAttendanceByNameAndWeek(2, "T02");
        Map<Student, ArrayList<String>> tempHolderComments = attendancelist.getCommentList();
        Map<Student, String> tempHolderAttendance = attendancelist.getAttendanceMap();
        Map<Student, String> ogAttendance = new HashMap<>(tempHolderAttendance);
        Map<Student, ArrayList<String>> ogComments = new HashMap<>(tempHolderComments);
        TutorialClass tutorial = attendancelist.getTutorialClass();
        //set same tut
        attendancelist.setTutorialClass(tutorial);
        assertEquals("T02", attendancelist.getTutorialClass().getTutorialName());
        Map<Student, ArrayList<String>> comments = attendancelist.getCommentList();
        Map<Student, String> attendanceMap = attendancelist.getAttendanceMap();
        for (Map.Entry<Student, ArrayList<String>> entry : comments.entrySet()) {
            assertTrue(entry.getValue().equals(ogComments.get(entry.getKey())));
        }
        for (Map.Entry<Student, String> entry : attendanceMap.entrySet()) {
            assertTrue(entry.getValue().equals(ogAttendance.get(entry.getKey())));
        }
        //change tut
        attendancelist.setTutorialClass(tutorial2);
        assertEquals("T01", attendancelist.getTutorialClass().getTutorialName());
        comments = attendancelist.getCommentList();
        attendanceMap = attendancelist.getAttendanceMap();
        for (Map.Entry<Student, ArrayList<String>> entry : comments.entrySet()) {
            assertTrue(entry.getValue().isEmpty());
        }
        for (Map.Entry<Student, String> entry : attendanceMap.entrySet()) {
            assertTrue(entry.getValue().equals("Absent"));
        }


    }
}
