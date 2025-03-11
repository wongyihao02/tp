package task;

/**
 * Represents a simple "Todo" task.
 * This class extends Task and defines the specific properties and methods for a Todo task.
 */
public class Todo extends Task {
    /**
     * Constructs a Todo task with the specified task name and completion status.
     *
     * @param taskName The name of the task.
     * @param done The status of the task (completed or not).
     */
    public Todo(String taskName, boolean done) {
        super(taskName, done);
        super.setTaskType(TaskType.TODO);
    }
    /**
     * Returns a string representation of the Todo task for file storage.
     *
     * @return A string representing the Todo task for file storage.
     */
    @Override
    public String toFileFormat() {
        return "T," + getIsDone() + "," + getTaskName() + ","+"\n";
    }


}
