package task;

import util.DateTimeFormatterUtil;
import exception.TASyncException;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Represents a task with a deadline.
 * This class extends the Task class and includes additional functionality for managing deadlines.
 */
public class Deadline extends Task {
    private final String deadline;
    /**
     * Constructs a Deadline task.
     *
     * @param taskName The name of the task.
     * @param done The status of the task (completed or not).
     * @param deadline The deadline for the task.
     */
    public Deadline(String taskName, boolean done, String deadline) throws TASyncException {
        super(taskName, done);

        if (!DateTimeFormatterUtil.isValidDateTime(deadline)) {
            throw new TASyncException("Invalid datetime format. Expected format: dd/MM/yyyy HH:mm");
        }

        this.deadline = deadline;
        setTaskType(TaskType.DEADLINE);
    }

    public String getDeadline() {
        return deadline;
    }

    /**
     * Checks if the deadline is due today.
     *
     * @param today The current date to check against.
     * @return True if the deadline is today, false otherwise.
     */
    public boolean isDueToday(LocalDate today) {
        if (!DateTimeFormatterUtil.isValidDateTime(deadline)) {
            return false;
        }
        LocalDateTime deadlineDateTime = DateTimeFormatterUtil.parseDateTime(deadline);
        assert deadlineDateTime != null;
        return deadlineDateTime.toLocalDate().equals(today);
    }

    /**
     * Prints the deadline of the task in a user-friendly format.
     */
    @Override
    public void printDue() {
        if (DateTimeFormatterUtil.isValidDateTime(deadline)) {
            System.out.println(" (by: " + DateTimeFormatterUtil.parseDateTime(deadline) + ")");
        } else {
            System.out.println(" (by: INVALID DATE FORMAT: " + deadline + ")");
        }
    }

    /**
     * Returns a string representation of the task in a file-friendly format.
     *
     * @return A string representing the task for file storage.
     */
    @Override
    public String toFileFormat() {
        String formattedDeadline = DateTimeFormatterUtil.isValidDateTime(deadline) ? deadline : "INVALID_DATE";
        return "D," + getIsDone() + "," + getTaskName() + "," + formattedDeadline + "\n";
    }
}
