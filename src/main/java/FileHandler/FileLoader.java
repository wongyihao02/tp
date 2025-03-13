package FileHandler;

import task.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
/**
 * Utility class for loading tasks from a file into a TaskList.
 * This class reads data from a file, parses it, and creates tasks that are added to the task list.
 */
public class FileLoader {
    private static final String FILE_PATH = "./data/Xb.txt";



    /**
     * Loads tasks from a file into a TaskList.
     * It parses the file content to recreate the tasks and add them to the list.
     *
     * @return A TaskList containing all the tasks loaded from the file.
     */
    public static TaskList loadTasks() {
        ArrayList<Task> tasks = new ArrayList<>();
        File f = new File(FILE_PATH);
        try {
            Scanner s = new Scanner(f);

        while (s.hasNext()) {
            String line = s.nextLine();
            String[] parts = line.split(",");
            if (parts.length < 3) continue; // Ignore malformed lines

            String type = parts[0];
            boolean isDone = parts[1].equals("true"); // Convert "1"/"0" to boolean
            String description = parts[2];

            switch (type) {
            case "T":
                tasks.add(new Todo(description, isDone));
                break;
            case "D":
                if (parts.length >= 4) {
                    String deadline = parts[3];
                    tasks.add(new Deadline(description, isDone, deadline));
                }
                break;
            case "E": // Event
                if (parts.length >= 4) {
                    String eventStart = parts[3];
                    String eventEnd = parts[4];
                    tasks.add(new Event(description, isDone, eventStart, eventEnd));
                }
                break;
            }
        }
        return new TaskList(tasks);
        }catch(FileNotFoundException e) {
            System.out.println("no existing data file found, new file will be created!");
            return new TaskList();
        }
    }
}
