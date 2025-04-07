package command.taskcommands;

import java.util.Scanner;

import util.DateTimeFormatterUtil;
import exception.TASyncException;
import task.Consultation;
import task.TaskList;

/**
 * Represents the "CONSULTATION" command that creates a consultation task with specific timings.
 * The command expects the student's name along with "/from" and "/to" tags for start and end times.
 */
public class ConsultationCommand implements Command<TaskList> {
    /**
     * Executes the "CONSULTATION" command by parsing the student name, start time, and end time,
     * and adding the consultation task to the task list.
     *
     * @param parts The command parts containing the student name and consultation timings.
     * @param taskList The task list where the new consultation task will be added.
     */
    @Override
    public void execute(String parts, TaskList taskList) {
        Scanner scanner = new Scanner(System.in);
        boolean commandValid = false;

        while (!commandValid) {
            try {
                if (!parts.contains("/from") || !parts.contains("/to")) {
                    throw new TASyncException(
                            "Invalid Consultation command. Specify duration with \"/from\" and \"/to\"."
                    );
                }

                // Split the input into expected parts
                String[] consultationParts = parts.split(" /from ", 2);
                if (consultationParts.length < 2) {
                    throw new TASyncException("Missing component. Please re-enter the full command.");
                }

                String studentName = consultationParts[0].trim();

                String[] timeParts = consultationParts[1].split(" /to ", 2);
                if (timeParts.length < 2) {
                    throw new TASyncException("Missing component. Please re-enter the full command.");
                }

                String from = timeParts[0].trim();
                String to = timeParts[1].trim();

                // Validate datetime format
                if (!DateTimeFormatterUtil.isValidDateTime(from) || !DateTimeFormatterUtil.isValidDateTime(to)) {
                    throw new TASyncException("Invalid datetime format. Expected format: dd/MM/yyyy HH:mm");
                }

                // Create and add the consultation task
                Consultation consultation = new Consultation(studentName, false, from, to);
                taskList.addTask(consultation);
                commandValid = true; // Exit loop since everything is valid

            } catch (TASyncException e) {
                System.out.println(e.getMessage());
                System.out.print("Please re-enter the full command without add -c\n");
                parts = scanner.nextLine().trim();
            }
        }
    }

}
