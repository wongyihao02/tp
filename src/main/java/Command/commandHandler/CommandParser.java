package Command.commandHandler;

/**
 * The CommandParser class is responsible for parsing a command string into parts.
 * It splits the input command into two parts: the command name and the associated data.
 * <p>
 * Example:
 * For the input "/add -pt Read book", it will split it into ["/add", "-pt", "Read book"].
 */
public class CommandParser {
    private String[] parts;

    /**
     * Constructs a CommandParser instance.
     * The constructor splits the input command string into two parts:
     * the command name and the associated data (if any).
     * <p>
     * If the command does not contain any spaces, the second part is set to an empty string.
     *
     * @param command The command string to be parsed.
     */
    public CommandParser(String command) {
        parts = command.split("\\s+", 3);

        // If the user only entered "/add" or "/add -c" without task details, handle gracefully
        if (parts.length == 1) {
            parts = new String[]{parts[0], "", ""}; // Ensure all parts exist
        } else if (parts.length == 2) {
            parts = new String[]{parts[0], parts[1], ""}; // Ensure task details exist
        }
    }

    /**
     * Returns the parsed command parts.
     *
     * @return An array of two elements: the command name and its associated data (if any).
     */
    public String[] getParts() {
        return parts;
    }

}
