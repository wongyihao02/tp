package util;

import command.taskcommands.CommandList;

/**
 * Utility class for printing all available commands.
 * This class iterates through the `CommandList` enum and prints descriptions for each command.
 */
public class CommandListPrinter {
    /**
     * Prints all available commands by calling the `printCommand` method for each command in the CommandList enum.
     */
    public static void printCommands() {
        System.out.println("Valid Commands:");

        // Loop through each enum constant in CommandList and call printCommand()
        for (CommandList command : CommandList.values()) {
            command.printCommand();
        }
    }
}
