package taskCommands;

import task.TaskList;
/**
 * Represents a generic command that can be executed.
 * Any command class must implement this interface and provide the execution logic.
 */
public interface taskCommand{
    /**
     * Executes the command with the provided parameters.
     *
     * @param parts The string parts that contain the necessary data for the command.
     * @param taskList The list of tasks that the command may manipulate.
     */
    void execute(String parts, TaskList taskList);
}
