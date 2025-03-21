package filehandlers;

import task.Task;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Saves a TaskList to a file.
 */
public class TaskListFileSaver implements FileSaver<ArrayList<Task>> {
    private static final String FILE_PATH = "./data/tasklist.txt";
    private static final String DIRECTORY_PATH = "./data";

    @Override
    public void saveToFile(ArrayList<Task> tasks, String directoryPath) {
        // Create the file if it doesn't exist
        FileCreator.createFileIfNotExists(FILE_PATH, DIRECTORY_PATH);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Task task : tasks) {
                writer.write(task.toFileFormat());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }
}
