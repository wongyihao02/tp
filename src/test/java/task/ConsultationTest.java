package task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;

import exception.TASyncException;

class ConsultationTest {

    @Test
    void testConsultationStartingToday() throws TASyncException {
        // Create a consultation starting today in "dd/MM/yyyy HH:mm" format
        String todayStr = LocalDate.now().format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy")) + " 14:00";
        String tomorrowStr = LocalDate.now().plusDays(1).
                format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy")) + " 16:00";

        Consultation consultation = new Consultation("John Doe", false, todayStr, tomorrowStr);

        // Call the method with today's date
        assertTrue(consultation.isStartingToday(LocalDate.now()));
    }

    @Test
    void testConsultationToFileFormat() throws TASyncException {
        Consultation consultation = new Consultation("Alice", true,
                "15/04/2025 09:30", "15/04/2025 11:00");

        assertEquals("C,true,Alice,15/04/2025 09:30,15/04/2025 11:00\n", consultation.toFileFormat());
    }
}
