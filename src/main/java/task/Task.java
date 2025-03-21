package task;
import java.util.logging.Logger;

/**
 * Abstract class representing a task.
 * This class defines the common properties and methods for all types of tasks
 */
public abstract class Task {
    private static final Logger logger = Logger.getLogger(Task.class.getName());

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
        if (this.isDone) {
            System.out.print("[X]"); //task done, print with X
        } else {
            System.out.print("[ ]"); //task not done print without X
        }
    }

    /**
     * Prints the task's type (e.g., Todo, Deadline, Event, Consultation).
     */
    public void printTaskType() {
        assert this.taskType != null : "TaskType is not initialized!";
        switch (this.getTaskType()) {
        case EVENT:
            System.out.print("[E]");
            break;
        case DEADLINE:
            System.out.print("[D]");
            break;
        case TODO:
            System.out.print("[T]");
            break;
        case CONSULTATION:
            System.out.print("[C]");
            break;
        default:
            break;
        }
    }
    /**
     * Prints the task details.
     */
    public void printTask() {
        printTaskType();
        printIsDone();
        System.out.print(" " + taskName);
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


