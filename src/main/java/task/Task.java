package task;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * Abstract class representing a task.
 * This class defines the common properties and methods for all types of tasks
 */
public abstract class Task {
    private static final Logger logger = Logger.getLogger(Task.class.getName());

    static {
        try {
            FileHandler fileHandler = new FileHandler("data/task-log.txt", true);
            fileHandler.setFormatter(new SimpleFormatter());
            logger.addHandler(fileHandler);
            logger.setUseParentHandlers(false);

            logger.info("Task logger initialized.");
        } catch (IOException e) {
            System.err.println("Failed to set up logger for Task: " + e.getMessage());
            e.printStackTrace();
        }
    }
    private TaskType taskType;
    private String taskName;
    private boolean isDone;

    /**
     * Constructs a Task with the given task name and completion status.
     *
     * @param taskName The name of the task.
     * @param done     The status of the task (completed or not).
     */
    public Task(String taskName, boolean done) {
        if (taskName == null || taskName.trim().isEmpty()) {
            logger.severe("Attempted to create a Task with an invalid name!");
            throw new IllegalArgumentException("Task name cannot be null or empty");
        }
        this.taskName = taskName;
        this.isDone = done;
    }

    public Task(String taskName) {
        assert taskName != null && !taskName.trim().isEmpty() : "Task name cannot be null or empty";
        this.taskName = taskName;
        this.isDone = false;
    }


    public TaskType getTaskType() {
        assert taskType != null : "TaskType must be set before usage!";
        return taskType;
    }

    public void setTaskType(TaskType taskType) {
        assert taskType != null : "TaskType cannot be set to null!";
        this.taskType = taskType;
    }

    public String getTaskName() {
        assert taskName != null && !taskName.trim().isEmpty() : "Task name cannot be null or empty";
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
        logger.info("Task name updated to: " + taskName);
    }

    public boolean getIsDone() {
        return isDone;
    }

    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }


    /**
     * Prints the task's completion status.
     */
    public void printIsDone() {
        String status = this.isDone ? "[X]" : "[ ]";
        logger.info("Task status: " + status);
        System.out.print(status);
    }

    /**
     * Prints the task's type (e.g., Todo, Deadline, Event, Consultation).
     */
    public void printTaskType() {
        assert this.taskType != null : "TaskType is not initialized!";
        String typeStr = "";
        switch (this.getTaskType()) {
        case EVENT:
            typeStr = "[E]";
            break;
        case DEADLINE:
            typeStr = "[D]";
            break;
        case TODO:
            typeStr = "[T]";
            break;
        case CONSULTATION:
            typeStr = "[C]";
            break;
        default:
            break;
        }
        logger.info("Task type: " + typeStr);
        System.out.print(typeStr);
    }
    /**
     * Prints the task details.
     */
    public void printTask() {
        String taskStr = getTaskTypeSymbol() + getIsDoneSymbol() + " " + taskName;
        logger.info("Task printed: " + taskStr);
        System.out.print(taskStr);
    }
    private String getTaskTypeSymbol() {
        return switch (this.getTaskType()) {
        case EVENT -> "[E]";
        case DEADLINE -> "[D]";
        case TODO -> "[T]";
        case CONSULTATION -> "[C]";
        };
    }
    private String getIsDoneSymbol() {
        return this.isDone ? "[X]" : "[ ]";
    }
    /**
     * Prints the due date or duration of the task (if applicable).
     */
    public void printDue() {
        System.out.println();
    }
    /**
     * Returns a string representation of the task for file storage.
     *
     * @return A string representing the task for file storage.
     */
    public abstract String toFileFormat();

}


