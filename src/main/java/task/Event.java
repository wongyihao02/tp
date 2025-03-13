package task;
import util.DateTimeFormatTool;
/**
 * Represents a task with an event.
 * This class extends the Task class and includes additional functionality for managing events with start and end times.
 */
public class Event extends Task {
    private String eventStart;
    private String eventEnd;
    /**
     * Constructs an Event task.
     *
     * @param taskName The name of the event.
     * @param done The status of the event (completed or not).
     * @param eventStart The start time of the event.
     * @param eventEnd The end time of the event.
     */
    public Event(String taskName, boolean done, String eventStart, String eventEnd) {
        super(taskName, done);
        this.eventStart = eventStart;
        this.eventEnd = eventEnd;
        setTaskType(TaskType.EVENT);
    }

    public String getEventEnd() {
        return eventEnd;
    }

    public String getEventStart() {
        return eventStart;
    }

    @Override
    public void printDue() {
        // If both eventStart and eventEnd are invalid, print raw values
        if (!DateTimeFormatTool.isValidDateTimeFormat(eventStart) && !DateTimeFormatTool.isValidDateTimeFormat(eventEnd)) {
            System.out.println(" (from: " + eventStart + " to: " + eventEnd + ")");
        }
        // If eventStart is valid but eventEnd is invalid, format eventStart, leave eventEnd raw
        else if (DateTimeFormatTool.isValidDateTimeFormat(eventStart) && !DateTimeFormatTool.isValidDateTimeFormat(eventEnd)) {
            System.out.println(" (from: " + DateTimeFormatTool.parseDateTime(eventStart) + " to: " + eventEnd + ")");
        }
        // If both eventStart and eventEnd are valid, format both
        else {
            System.out.println(" (from: " + DateTimeFormatTool.parseDateTime(eventStart) + " to: " + DateTimeFormatTool.parseDateTime(eventEnd) + ")");
        }
    }
    /**
     * Returns a string representation of the event task for file storage.
     *
     * @return A string representing the event task for file storage.
     */
    @Override
    public String toFileFormat() {
        return "E," + getIsDone() + "," + getTaskName() + "," + getEventStart() +"," +getEventEnd()+"\n";
    }


}
