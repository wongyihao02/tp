package filehandlers;

import exception.TASyncException;
import task.Consultation;
import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

//@@author kmnyn
public class TaskParser {
    /**
     * Parses a line from the file and converts it into the corresponding Task object.
     *
     * @param line A single line from the file.
     * @return The corresponding Task object (Todo, Deadline, Event, or Consultation).
     */
    public static Task parseTaskFromFile(String line) throws TASyncException {
        // Skip empty lines or lines containing only whitespace
        if (line.trim().isEmpty()) {
            return null;  // Return null or handle it in a way you prefer (e.g., skip it)
        }

        String[] parts = line.split(",", -1);
        if (parts.length < 3) {
            throw new IllegalArgumentException("Invalid task format: " + line);
        }

        String type = parts[0];        // Task type (T, D, E, C)
        boolean isDone = Boolean.parseBoolean(parts[1]);
        String taskName = parts[2];

        switch (type) {
        case "T":
            return new Todo(taskName, isDone);
        case "D":
            if (parts.length < 4) {
                throw new IllegalArgumentException("Invalid Deadline format: " + line);
            }
            return new Deadline(taskName, isDone, parts[3]);
        case "E":
            if (parts.length < 5) {
                throw new IllegalArgumentException("Invalid Event format: " + line);
            }
            return new Event(taskName, isDone, parts[3], parts[4]);
        case "C":
            if (parts.length < 5) {
                throw new IllegalArgumentException("Invalid Consultation format: " + line);
            }
            return new Consultation(taskName, isDone, parts[3], parts[4]);
        default:
            throw new IllegalArgumentException("Unknown task type: " + type);
        }
    }
}
