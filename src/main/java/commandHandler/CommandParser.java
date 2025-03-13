package commandHandler;

/**
 * The CommandParser class is responsible for parsing a command string into parts.
 * It splits the input command into two parts: the command name and the associated data.
 * <p>
 * Example:
 * For the input "TODO Read book", it will split it into ["TODO", "Read book"].
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
        parts = command.split("\\s+", 2);
        if (parts.length == 1) {
            parts = new String[]{parts[0], ""};
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
