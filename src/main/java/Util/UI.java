package Util;

import commandHandler.CommandHandler;
import commandHandler.CommandParser;
import task.TaskList;

import java.util.Scanner;

import static FileHandler.FileSaver.saveTasks;
/**
 * User Interface (UI) class for interacting with the user.
 * This class handles user input, processes commands, and interacts with the task list.
 */
public class UI {
    Scanner s = new Scanner(System.in);
    /**
     * Constructs a UI instance and initializes the interaction with the user.
     * It prompts for the user's name and allows them to enter commands to manipulate the task list.
     *
     * @param taskList The task list that the UI will interact with.
     */
    public UI(TaskList taskList) {
        System.out.println("Hello, I am xb!");
        System.out.println("What is your name?");
        String name = s.nextLine();
        System.out.print(name);
        System.out.println(", what can I do for you?");
        String command;

        boolean run = true;
        while (run) {
            command = s.nextLine().trim();
            CommandParser cp = new CommandParser(command);
            CommandHandler c = new CommandHandler(taskList, cp.getParts());
                run = c.runCommand();

        }
        saveTasks(taskList);
        s.close();
    }

}

