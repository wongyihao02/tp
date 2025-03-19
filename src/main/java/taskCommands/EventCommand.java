package taskCommands;

import java.util.Scanner;

import Util.DateTimeFormatterUtil;
import exception.TASyncException;
import task.Event;
import task.TaskList;
/**
 * Represents the "EVENT" command that creates a task with event timings.
 * The command expects the description of the event and "/from" and "/to" tags for start and end times.
 */
public class EventCommand implements Command<TaskList> {
    /**
     * Executes the "EVENT" command by parsing the task description, start time, and end time,
     * and adding the event task to the task list.
     *
     * @param parts The command parts containing the task description and event timings.
     * @param taskList The task list where the new event task will be added.
     */
    @Override
    public void execute(String parts, TaskList taskList) {
        Scanner scanner = new Scanner(System.in);
        boolean commandValid = false;

        while (!commandValid) {
            try {
                // Ensure the input contains both /from and /to
                if (!parts.contains("/from") || !parts.contains("/to")) {
                    throw new TASyncException("Invalid Event command. Specify duration with \"/from\" and \"/to\".");
                }

                // Split the input into expected parts
                String[] eventParts = parts.split(" /from ", 2);
                if (eventParts.length < 2) {
                    throw new TASyncException("Missing start time. Please re-enter the full command.");
                }

                String taskName = eventParts[0].trim();

                String[] timeParts = eventParts[1].split(" /to ", 2);
                if (timeParts.length < 2) {
                    throw new TASyncException("Missing end time. Please re-enter the full command.");
                }

                String from = timeParts[0].trim();
                String to = timeParts[1].trim();

                // Validate datetime format
                if (!DateTimeFormatterUtil.isValidDateTime(from) || !DateTimeFormatterUtil.isValidDateTime(to)) {
                    throw new TASyncException("Invalid datetime format. Expected format: dd/MM/yyyy HH:mm");
                }

                // Create and add the event task
                Event event = new Event(taskName, false, from, to);
                taskList.addTask(event);
                commandValid = true; // Exit loop since everything is valid

            } catch (TASyncException e) {
                System.out.println(e.getMessage());
                System.out.print("Please re-enter the full command without /add -pe\n");
                parts = scanner.nextLine().trim();
            }
        }
    }

}
