package util;

import java.util.Scanner;

import task.TaskList;
import tutorial.TutorialClassList;

public class UI {
    private final Scanner scanner;

    public UI() {
        scanner = new Scanner(System.in);
    }

    /**
     * Prints the welcome message when the program starts.
     */
    public void printWelcome() {
        System.out.println("👋 Welcome to TASync!");
        System.out.println("Type a command to get started, or type HELP to see available commands.");
        System.out.println("----------------------------------------------------");
    }

    /**
     * Display the daily schedule using the DailySchedule class.
     *
     * @param taskList         List of tasks
     * @param tutorialList     List of tutorial classes
     */
    public void displayDailySchedule(TaskList taskList, TutorialClassList tutorialList) {
        DailySchedule dailySchedule = new DailySchedule(taskList, tutorialList);
        dailySchedule.displayScheduleForToday();
    }

    /**
     * Prints a generic message to the user.
     *
     * @param message The message to print.
     */
    public void printMessage(String message) {
        System.out.println(message);
    }

    /**
     * Reads the next user command from the console.
     *
     * @return The user input as a string.
     */
    public String getUserCommand() {
        System.out.print("> "); // Prompt
        return scanner.nextLine().trim();
    }

    /**
     * Prints a goodbye message.
     */
    public void printGoodbye() {
        System.out.println("👋 Goodbye! Have a productive day!");
    }

    /**
     * Close scanner when done (optional).
     */
    public void close() {
        scanner.close();
    }
}
