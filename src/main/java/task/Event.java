package task;
import util.DateTimeFormatterUtil;
import exception.TASyncException;


import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Represents a task with an event.
 * This class extends the Task class and includes additional functionality for managing events with start and end times.
 */
public class Event extends Task {
    private final String eventStart;
    private final String eventEnd;
    /**
     * Constructs an Event task.
     *
     * @param taskName The name of the event.
     * @param done The status of the event (completed or not).
     * @param eventStart The start time of the event.
     * @param eventEnd The end time of the event.
     */
    public Event(String taskName, boolean done, String eventStart, String eventEnd) throws TASyncException {
        super(taskName, done);

        if (!DateTimeFormatterUtil.isValidDateTime(eventStart) || !DateTimeFormatterUtil.isValidDateTime(eventEnd)) {
            throw new TASyncException("Invalid datetime format. Expected format: dd/MM/yyyy HH:mm");
        }

        this.eventStart = eventStart;
        this.eventEnd = eventEnd;
        setTaskType(TaskType.EVENT);
    }

    public String getEventStart() {
        return eventStart;
    }

    public String getEventEnd() {
        return eventEnd;
    }

    /**
     * Checks if the event starts today.
     *
     * @param today The current date to check against.
     * @return True if the event starts today, false otherwise.
     */
    public boolean isStartingToday(LocalDate today) {
        if (!DateTimeFormatterUtil.isValidDateTime(eventStart)) {
            return false;
        }
        LocalDateTime startDateTime = DateTimeFormatterUtil.parseDateTime(eventStart);
        assert startDateTime != null;
        return startDateTime.toLocalDate().equals(today);
    }

    @Override
    public void printDue() {
        boolean isStartValid = DateTimeFormatterUtil.isValidDateTime(eventStart);
        boolean isEndValid = DateTimeFormatterUtil.isValidDateTime(eventEnd);

        String formattedStart = isStartValid
                ? String.valueOf(DateTimeFormatterUtil.parseDateTime(eventStart))
                : "INVALID DATE";
        String formattedEnd = isEndValid
                ? String.valueOf(DateTimeFormatterUtil.parseDateTime(eventEnd))
                : "INVALID DATE";

        System.out.println(" (from: " + formattedStart + " to: " + formattedEnd + ")");
    }

    /**
     * Returns a string representation of the event task for file storage.
     *
     * @return A string representing the event task for file storage.
     */
    @Override
    public String toFileFormat() {
        String start = DateTimeFormatterUtil.isValidDateTime(eventStart)
                ? eventStart
                : "INVALID_DATE";

        String end = DateTimeFormatterUtil.isValidDateTime(eventEnd)
                ? eventEnd
                : "INVALID_DATE";

        return "E," + getIsDone() + "," + getTaskName() + "," + start + "," + end + "\n";
    }

}
