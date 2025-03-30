package command.commandhandler;

import command.taskcommands.ByeCommand;
import command.taskcommands.Command;

/**
 * The CommandHandler class is responsible for executing commands by interacting with the TaskList.
 * It uses the Command object created by the CommandFactory to execute specific tasks.
 * <p>
 * Example:
 * Given a command like "TODO Read book", it will find the appropriate command (e.g., TodoCommand)
 * and call the execute method with the necessary parts.
 */
public class CommandHandler<T> {
    private final T list;
    private final Command taskCommand;
    private String parts;

    /**
     * Constructs a CommandHandler instance with the TaskList and command parts.
     * <p>
     * The constructor takes in an array of command strings, where the first element is the command name
     * (which is used to create the Command object), and the second element is the associated data.
     *
     * @param list The TaskList/StudentList object that the command will operate on.
     * @param commands An array containing the command name and its associated data.
     */
    public CommandHandler(T list, String[] commands) {
        this.list = list;
        // Ensure the input is valid before proceeding
        if (commands.length < 2) {
            System.out.println("Invalid command format. Please use: add -[type] [task details]");
            taskCommand = null;
            return;
        }

        // Reconstruct the original command string to pass it to CommandFactory
        String fullCommand = String.join(" ", commands);
        taskCommand = CommandFactory.createCommand(fullCommand);

        // Extract task details if available
        this.parts = (commands.length > 2) ? commands[2] : "";

    }

    /**
     * Executes the current command. If the command is valid, it will invoke its execute method.
     * If the command is of type ByeCommand, it returns false to indicate the termination of the program.
     *
     * @return true if the command is not of type ByeCommand; false otherwise.
     */
    public boolean runCommand() {
        if (taskCommand != null && list != null) {
            taskCommand.execute(parts, list);
        }
        return !(taskCommand instanceof ByeCommand);
    }
}
