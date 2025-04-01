package filehandlers;

import tutorial.TutorialClass;
import tutorial.TutorialClassList;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


class TutorialClassListFileLoaderTest {

    @Test
    void testLoadFromFile_withMultipleTutorials() throws IOException {
        // Step 1: Create sample data
        String sampleData = """
            # T01,MONDAY,09:00,10:30
            Alice Tan,13/03/2002,Female,81234567,A001,Class Rep
            Ben Lim,21/04/2002,Male,81234568,A002,
            
            # T02,TUESDAY,11:00,12:30
            Isaac Tan,13/03/2001,Male,81234575,A009,
            Jasmine Goh,21/04/2001,Female,81234576,A010,
            
            # T03,TUESDAY,13:00,14:30
            Quincy Tan,13/03/2000,Male,81234583,A017,
            Rachel Lim,21/04/2000,Female,81234584,A018,
            
            # T04,TUESDAY,15:00,16:30
            Yvonne Lee,13/03/1999,Female,81234591,A025,
            Zachary Teo,21/04/1999,Male,81234592,A026,
            
            # T05,FRIDAY,17:00,18:30
            Gina Yeo,13/03/1998,Female,81234599,A033,
            Henry Lim,21/04/1998,Male,81234600,A034,
            """;

        // Step 2: Write to a temporary file
        Path tempFile = Files.createTempFile("tutorial_classes", ".txt");
        Files.writeString(tempFile, sampleData);

        // Step 3: Load using your file loader
        TutorialClassListFileLoader loader = new TutorialClassListFileLoader();
        TutorialClassList tutorialList = loader.loadFromFile(tempFile.toString());

        // Step 4: Assert
        List<TutorialClass> tutorials = tutorialList.getTutorialClasses();
        assertEquals(5, tutorials.size(), "Should load 5 tutorial classes");

        TutorialClass t01 = tutorialList.getTutorialByName("T01");
        assertNotNull(t01, "T01 should be loaded");
        assertEquals(DayOfWeek.MONDAY, t01.getDayOfWeek());
        assertEquals(LocalTime.of(9, 0), t01.getStartTime());
        assertEquals(LocalTime.of(10, 30), t01.getEndTime());
        assertEquals(2, t01.getStudentList().getStudents().size(), "T01 should have 2 students");

        TutorialClass t05 = tutorialList.getTutorialByName("T05");
        assertNotNull(t05, "T05 should be loaded");
        assertEquals(DayOfWeek.FRIDAY, t05.getDayOfWeek());
        assertEquals(2, t05.getStudentList().getStudents().size(), "T05 should have 2 students");

        // Clean up temp file
        Files.deleteIfExists(tempFile);
    }
}
