package marks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class MarksListTest {
    private MarksList marksList;
    private Marks projectMarks;
    private Marks examMarks;

    @BeforeEach
    public void setUp() {
        marksList = new MarksList();
        projectMarks = new Marks("Project", 85, 100);
        examMarks = new Marks("Exam", 90, 100);
    }

    @Test
    public void testAddMarks() {
        marksList.addMarks(projectMarks);
        assertEquals(1, marksList.getMarksList().size());
        assertTrue(marksList.getMarksList().contains(projectMarks));
    }

    @Test
    public void testDeleteMarks() {
        marksList.addMarks(projectMarks);
        marksList.addMarks(examMarks);
        marksList.deleteMarks(projectMarks);

        assertEquals(1, marksList.getMarksList().size());
        assertFalse(marksList.getMarksList().contains(projectMarks));
        assertTrue(marksList.getMarksList().contains(examMarks));
    }

    @Test
    public void testGetByAssignmentName_found() {
        marksList.addMarks(projectMarks);
        Marks retrieved = marksList.getByAssignmentName("Project");
        assertNotNull(retrieved);
        assertEquals(projectMarks, retrieved);
    }

    @Test
    public void testGetByAssignmentName_ignoreCase() {
        marksList.addMarks(projectMarks);
        Marks retrieved = marksList.getByAssignmentName("projECT");
        assertNotNull(retrieved);
        assertEquals(projectMarks, retrieved);
    }

    @Test
    public void testGetByAssignmentName_notFound() {
        marksList.addMarks(projectMarks);
        Marks retrieved = marksList.getByAssignmentName("Task");
        assertNull(retrieved);
    }

    @Test
    public void testSetMarksList() {
        ArrayList<Marks> newList = new ArrayList<>();
        newList.add(projectMarks);
        newList.add(examMarks);

        marksList.setMarksList(newList);
        assertEquals(2, marksList.getMarksList().size());
        assertTrue(marksList.getMarksList().contains(projectMarks));
        assertTrue(marksList.getMarksList().contains(examMarks));
    }
}

