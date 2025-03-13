package taskCommands;

import exception.TASyncException;
import task.Event;
import task.TaskList;
/**
 * Represents the "EVENT" command that creates a task with event timings.
 * The command expects the description of the event and "/from" and "/to" tags for start and end times.
 */
public class EventCommand implements taskCommand {
    /**
     * Executes the "EVENT" command by parsing the task description, start time, and end time,
     * and adding the event task to the task list.
     *
     * @param parts The command parts containing the task description and event timings.
     * @param taskList The task list where the new event task will be added.
     */
    @Override
    public void execute(String parts, TaskList taskList) {
        try {
            if (parts.contains("/from") && parts.contains("/to")) {
                String[] eventParts = parts.split("/from|/to");
                String taskName = eventParts[0].trim();
                String from = eventParts[1].trim();
                String to = eventParts[2].trim();
                Event event = new Event(taskName, false, from, to);
                taskList.addTask(event);
            } else {
                throw TASyncException.invalidEventCommand();
            }
        } catch (TASyncException e) {
            System.out.println(e.getMessage());
        }
    }


}
