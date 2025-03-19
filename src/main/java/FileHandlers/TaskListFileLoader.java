package FileHandlers;

import exception.TASyncException;
import task.Task;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Loads a TaskList from a file.
 */
public class TaskListFileLoader implements FileLoader<ArrayList<Task>> {
    private static final String FILE_PATH = "./data/tasklist.txt";

    @Override
    public ArrayList<Task> loadFromFile(String filePath) {
        FileCreator.createFileIfNotExists(FILE_PATH, "./data");
        ArrayList<Task> tasks = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Skip empty lines
                if (line.trim().isEmpty()) {
                    continue;
                }

                // Parse the task from the line
                Task task = TaskParser.parseTaskFromFile(line);
                if (task != null) {  // Ensure only valid tasks are added
                    tasks.add(task);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        } catch (TASyncException e) {
            throw new RuntimeException(e);
        }
        return tasks;  // Return the tasks list directly
    }


}
