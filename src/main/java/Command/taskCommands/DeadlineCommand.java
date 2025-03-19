package Command.taskCommands;

import exception.TASyncException;
import task.Deadline;
import task.TaskList;
import Util.DateTimeFormatterUtil;

import java.util.Scanner;

/**
 * Represents the "DEADLINE" command that creates a task with a deadline.
 * The command expects the description of the task and a "/by" tag for the deadline.
 */
public class DeadlineCommand implements Command<TaskList> {
    /**
     * Executes the "DEADLINE" command by parsing the task description and deadline,
     * and adding the deadline task to the task list.
     *
     * @param parts The command parts containing the task description and deadline.
     * @param taskList The task list where the new deadline task will be added.
     */
    @Override
    public void execute(String parts, TaskList taskList) {
        Scanner scanner = new Scanner(System.in);
        boolean commandValid = false;

        while (!commandValid) {
            try {
                // Ensure the command contains "/by"
                if (!parts.contains("/by")) {
                    throw new TASyncException("Invalid Deadline command. Specify the deadline using \"/by\".");
                }

                // Split into description and deadline
                String[] deadlineParts = parts.split(" /by ", 2);
                if (deadlineParts.length < 2) {
                    throw new TASyncException("Missing deadline. Please re-enter the full command.");
                }

                String taskName = deadlineParts[0].trim();
                String deadlineInput = deadlineParts[1].trim();

                // Validate deadline format
                if (!DateTimeFormatterUtil.isValidDateTime(deadlineInput)) {
                    throw new TASyncException("Invalid datetime format. Expected format: dd/MM/yyyy HH:mm");
                }

                // Create and add the deadline task
                Deadline dl = new Deadline(taskName, false, deadlineInput);
                taskList.addTask(dl);
                commandValid = true; // Exit loop since everything is valid

            } catch (TASyncException e) {
                System.out.println(e.getMessage());
                System.out.print("Please re-enter the full command without add -pd\n");
                parts = scanner.nextLine().trim();
            }
        }
    }

}

