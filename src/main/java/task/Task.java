package task;

/**
 * Abstract class representing a task.
 * This class defines the common properties and methods for all types of tasks (e.g., Todo, Deadline, Event).
 */
public abstract class Task {
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
        this.taskName = taskName;
        this.isDone = done;
    }

    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }


    public TaskType getTaskType() {
        return taskType;
    }

    public void setTaskType(TaskType taskType) {
        this.taskType = taskType;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
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
     * Prints the task's type (e.g., Todo, Deadline, Event).
     */
    public void printTaskType() {
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


