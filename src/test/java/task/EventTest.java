package task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;

import exception.TASyncException;

class EventTest {
    @Test
    void testEventStartingToday() throws TASyncException {
        // Create an event starting today in "dd/MM/yyyy HH:mm" format
        String todayStr = LocalDate.now().format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy")) + " 10:00";
        String tomorrowStr = LocalDate.now().plusDays(1).
                format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy")) + " 12:00";

        Event event = new Event("Workshop", false, todayStr, tomorrowStr);

        // Call the method with today's date
        assertTrue(event.isStartingToday(LocalDate.now()));
    }

    @Test
    void testEventToFileFormat() throws TASyncException {
        Event event = new Event("Hackathon", true, "20/05/2025 08:00", "21/05/2025 18:00");

        assertEquals("E,true,Hackathon,20/05/2025 08:00,21/05/2025 18:00\n", event.toFileFormat());
    }
}
