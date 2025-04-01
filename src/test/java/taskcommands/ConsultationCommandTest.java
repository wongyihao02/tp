package taskcommands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import command.taskcommands.ConsultationCommand;
import exception.TASyncException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import task.Consultation;
import task.TaskList;

import java.util.ArrayList;

class ConsultationCommandTest {
    private TaskList taskList;

    @BeforeEach
    void setUp() {
        taskList = new TaskList(new ArrayList<>());
    }

    @AfterEach
    void tearDown() {
        taskList = null; // Ensure TaskList is reset
    }

    @Test
    void execute_validInput() throws TASyncException {
        ConsultationCommand command = new ConsultationCommand();
        int initialSize = taskList.getTasks().size();
        String validCommand = "John Doe /from 01/04/2025 10:00 /to 01/04/2025 11:00";

        command.execute(validCommand, taskList);

        assertEquals(initialSize + 1, taskList.getTasks().size(),
                "Task list size should increase by 1.");
        Consultation addedConsultation = (Consultation) taskList.getTasks().get(initialSize);
        assertEquals("John Doe", addedConsultation.getTaskName());
        assertEquals("01/04/2025 10:00", addedConsultation.getConsultationStart());
        assertEquals("01/04/2025 11:00", addedConsultation.getConsultationEnd());
    }
}
