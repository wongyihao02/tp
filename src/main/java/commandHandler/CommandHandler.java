package commandHandler;

import taskCommands.ByeCommand;
import taskCommands.taskCommand;
import task.TaskList;

/**
 * The CommandHandler class is responsible for executing commands by interacting with the TaskList.
 * It uses the Command object created by the CommandFactory to execute specific tasks.
 * <p>
 * Example:
 * Given a command like "TODO Read book", it will find the appropriate command (e.g., TodoCommand)
 * and call the execute method with the necessary parts.
 */
public class CommandHandler {
    private TaskList taskList;
    private taskCommand taskCommand;
    private String parts;

    /**
     * Constructs a CommandHandler instance with the TaskList and command parts.
     * <p>
     * The constructor takes in an array of command strings, where the first element is the command name
     * (which is used to create the Command object), and the second element is the associated data.
     *
     * @param taskList The TaskList object that the command will operate on.
     * @param commands An array containing the command name and its associated data.
     */
    public CommandHandler(TaskList taskList, String[] commands) {
        this.taskList = taskList;
        taskCommand = CommandFactory.createCommand(commands[0]);
        parts = commands[1];

    }

    /**
     * Executes the current command. If the command is valid, it will invoke its execute method.
     * If the command is of type ByeCommand, it returns false to indicate the termination of the program.
     *
     * @return true if the command is not of type ByeCommand; false otherwise.
     */
    public boolean runCommand() {
        if (taskCommand != null) {
            taskCommand.execute(parts, taskList);
        }
        return !(taskCommand instanceof ByeCommand);
    }
}