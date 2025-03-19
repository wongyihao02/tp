package task;

import Util.DateTimeFormatterUtil;
import exception.TASyncException;

/**
 * Represents a task with a consultation.
 * This class extends the Task class and includes additional functionality for managing consultations with start and end times.
 */
public class Consultation extends Task {
    private final String consultationStart;
    private final String consultationEnd;

    /**
     * Constructs a Consultation task.
     *
     * @param studentName       The name of the student.
     * @param done              The status of the consultation (completed or not).
     * @param consultationStart The start time of the consultation.
     * @param consultationEnd   The end time of the consultation.
     */
    public Consultation(String studentName, boolean done, String consultationStart, String consultationEnd) throws TASyncException {
        super(studentName, done);

        if (!DateTimeFormatterUtil.isValidDateTime(consultationStart) || !DateTimeFormatterUtil.isValidDateTime(consultationEnd)) {
            throw new TASyncException("Invalid datetime format. Expected format: dd/MM/yyyy HH:mm");
        }

        this.consultationStart = consultationStart;
        this.consultationEnd = consultationEnd;
        setTaskType(TaskType.CONSULTATION);
    }

    @Override
    public void printDue() {
        boolean isStartValid = DateTimeFormatterUtil.isValidDateTime(consultationStart);
        boolean isEndValid = DateTimeFormatterUtil.isValidDateTime(consultationEnd);

        String formattedStart = isStartValid ? String.valueOf(DateTimeFormatterUtil.parseDateTime(consultationStart)) : "INVALID DATE";
        String formattedEnd = isEndValid ? String.valueOf(DateTimeFormatterUtil.parseDateTime(consultationEnd)) : "INVALID DATE";

        System.out.println(" (from: " + formattedStart + " to: " + formattedEnd + ")");
    }

    /**
     * Returns a string representation of the consultation task for file storage.
     *
     * @return A string representing the consultation task for file storage.
     */

    @Override
    public String toFileFormat() {
        String start = DateTimeFormatterUtil.isValidDateTime(consultationStart)
                ? consultationStart
                : "INVALID_DATE";

        String end = DateTimeFormatterUtil.isValidDateTime(consultationEnd)
                ? consultationEnd
                : "INVALID_DATE";

        return "C," + getIsDone() + "," + getTaskName() + "," + start + "," + end + "\n";
    }
}
