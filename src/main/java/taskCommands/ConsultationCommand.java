package taskCommands;

import exception.TASyncException;
import task.Consultation;
import task.TaskList;

/**
 * Represents the "CONSULTATION" command that creates a consultation task with specific timings.
 * The command expects the student's name along with "/from" and "/to" tags for start and end times.
 */
public class ConsultationCommand implements taskCommand {
    /**
     * Executes the "CONSULTATION" command by parsing the student name, start time, and end time,
     * and adding the consultation task to the task list.
     *
     * @param parts The command parts containing the student name and consultation timings.
     * @param taskList The task list where the new consultation task will be added.
     */
    @Override
    public void execute(String parts, TaskList taskList) {
        try {
            if (parts.contains("/from") && parts.contains("/to")) {
                String[] consultParts = parts.split("/from|/to");
                String studentName = consultParts[0].trim();
                String from = consultParts[1].trim();
                String to = consultParts[2].trim();
                Consultation consultation = new Consultation(studentName, false, from, to);
                taskList.addTask(consultation);
            } else {
                throw TASyncException.invalidConsultationCommand();
            }
        } catch (TASyncException e) {
            System.out.println(e.getMessage());
        }
    }
}
