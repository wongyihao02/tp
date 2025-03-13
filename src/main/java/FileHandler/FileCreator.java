package FileHandler;

import java.io.File;
import java.io.IOException;
/**
 * Utility class for creating files and directories if they don't already exist.
 * This class ensures that a given file path and its corresponding directory are created before accessing or writing to them.
 */
public class FileCreator {
    /**
     * Creates a directory and file if they don't already exist.
     *
     * @param filePath The path to the file that needs to be created.
     * @param directoryPath The path to the directory where the file will be created.
     */
    public static void createFileIfNotExists(String filePath, String directoryPath) {
            File file = new File(filePath);
            File directory = new File(directoryPath);

            try {
                if (!directory.exists()) {
                    boolean dirCreated = directory.mkdirs();
                    if (dirCreated) {
                        System.out.println("Created directory: " + directoryPath);
                    }
                }

                if (!file.exists()) {
                    boolean fileCreated = file.createNewFile(); // Creates file if missing
                    if (fileCreated) {
                        System.out.println("Created file: " + filePath);
                    }
                } else {
                    System.out.println("File already exists: " + filePath);
                }
            } catch (IOException e) {
                System.out.println("Error creating file: " + e.getMessage());
            }
        }
}
