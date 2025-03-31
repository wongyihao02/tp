package filehandlers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import attendance.AttendanceFile;
import attendance.AttendanceList;
import tutorial.TutorialClassList;




public class AttendanceFileFileLoaderTest {

    private AttendanceFile attendanceFileForTest;
    private TutorialClassList tutorialClassListForTest;

    @Test
    void testLoadAttendanceFile() throws IOException {
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

        Path tempFile2 = Files.createTempFile("attendanceFile", ".txt");
        Files.writeString(tempFile2, attendancefiletext);

        // Step 3: Load using your file loader
        TutorialClassListFileLoader loader = new TutorialClassListFileLoader();
        TutorialClassList tutorialList = loader.loadFromFile(tempFile.toString());

        AttendanceFileFileLoader loader2 = new AttendanceFileFileLoader(tutorialList);
        AttendanceFile attendanceFile = loader2.loadFromFile(tempFile2.toString());
        ArrayList<AttendanceList> attendanceFileList = attendanceFile.getAttendanceList();
        //ensure correct num of attendance lists loaded
        assertEquals(7, attendanceFileList.size(), "Should load 7 attendance files");

        AttendanceList to1w1 = attendanceFile.getAttendanceByNameAndWeek(1, "T01");
        assertNotNull(to1w1, "T01 should be loaded");

        AttendanceList t02w3 = attendanceFile.getAttendanceByNameAndWeek(2, "T02");
        assertNotNull(t02w3, "T02 should be loaded");

        attendanceFileForTest = attendanceFile;
        tutorialClassListForTest = tutorialList;

        Files.deleteIfExists(tempFile);
        Files.deleteIfExists(tempFile2);
    }
}
