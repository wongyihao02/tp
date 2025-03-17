package task;
import Util.DateTimeFormatterUtil;
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
     * @param studentName The name of the student.
     * @param done The status of the consultation (completed or not).
     * @param consultationStart The start time of the consultation.
     * @param consultationEnd The end time of the consultation.
     */
    public Consultation(String studentName, boolean done, String consultationStart, String consultationEnd) {
        super(studentName, done);
        this.consultationStart = consultationStart;
        this.consultationEnd = consultationEnd;
        setTaskType(TaskType.CONSULTATION);
    }

    public String getConsultationEnd() {
        return consultationEnd;
    }

    public String getConsultationStart() {
        return consultationStart;
    }

    @Override
    public void printDue() {
        // If both consultationStart and consultationEnd are invalid, print raw values
        boolean isConsultationEndValidDateTime = DateTimeFormatterUtil.isValidDateTime(consultationEnd);
        boolean isConsultationStartValidDateTime = DateTimeFormatterUtil.isValidDateTime(consultationStart);

        if (!isConsultationStartValidDateTime && !isConsultationEndValidDateTime) {
            System.out.println(" (from: " + consultationStart + " to: " + consultationEnd + ")");
        }
        // If eventStart is valid but consultationEnd is invalid, format consultationStart, leave consultationEnd raw
        else if (isConsultationStartValidDateTime && !isConsultationEndValidDateTime) {
            System.out.println(" (from: " + DateTimeFormatterUtil.parseDateTime(consultationStart) + " to: " + consultationEnd + ")");
        }
        // If both consultationStart and consultationEnd are valid, format both
        else {
            System.out.println(" (from: " + DateTimeFormatterUtil.parseDateTime(consultationStart) + " to: " + DateTimeFormatterUtil.parseDateTime(consultationEnd) + ")");
        }
    }
    /**
     * Returns a string representation of the consultation task for file storage.
     *
     * @return A string representing the consultation task for file storage.
     */
    @Override
    public String toFileFormat() {
        return "C," + getIsDone() + "," + getTaskName() + "," + getConsultationStart() +"," +getConsultationEnd()+"\n";
    }


}
