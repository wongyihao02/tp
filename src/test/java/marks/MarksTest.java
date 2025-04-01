package marks;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MarksTest {

    @Test
    public void testConstructorAndGetters() {
        Marks marks = new Marks("Practical Test", 85, 100);
        assertEquals("Practical Test", marks.getAssignmentName());
        assertEquals(85, marks.getMarks());
        assertEquals(100, marks.getMaxMark());
    }

    @Test
    public void testSetters() {
        Marks marks = new Marks("Science Test", 70, 100);
        marks.setAssignmentName("Physics Test");
        marks.setMarks(90);
        marks.setMaxMark(120);

        assertEquals("Physics Test", marks.getAssignmentName());
        assertEquals(90, marks.getMarks());
        assertEquals(120, marks.getMaxMark());
    }

    @Test
    public void testToString() {
        Marks marks = new Marks("Final Project", 45, 50);
        assertEquals("Assignment: Final Project. Marks achieved: 45/50", marks.toString());
    }

    @Test
    public void testToFileFormat() {
        Marks marks = new Marks("CS Lab", 88, 100);
        assertEquals("CS Lab,88,100", marks.toFileFormat());
    }
}

