package Command.taskCommands;

/**
 * Represents a generic command that can be executed.
 * Any command class must implement this interface and provide the execution logic.
 *
 * @param <T> The type of object the command will operate on.
 */
public interface Command<T> {
    /**
     * Executes the command with the provided parameters.
     *
     * @param parts The string parts that contain the necessary data for the command.
     * @param target The object (e.g., TaskList or something else) that the command operates on.
     */
    void execute(String parts, T target);
}