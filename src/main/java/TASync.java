
import task.TaskList;
import util.UI;

import FileHandler.FileCreator;

import static FileHandler.FileLoader.loadTasks;
/**
 * Main class to run the Xb application.
 * This class initializes the task list, sets up necessary files, and starts the user interface (UI).
 */
public class TASync {
    private static final String DIRECTORY_PATH = "./data";
    private static final String FILE_PATH = "./data/Xb.txt";
    /**
     * Main method to run the TASync application.
     * It creates necessary directories and files, loads tasks from a file, and starts the user interface.
     *
     * @param args Command-line arguments (not used in this case).
     */
    public static void main(String[] args) {
        FileCreator.createFileIfNotExists(FILE_PATH, DIRECTORY_PATH);
        TaskList taskList = loadTasks();
        UI ui = new UI(taskList);

    }


}

