package exception;
/**
 * Custom exception class for handling various invalid command inputs in the application.
 * This class provides specific methods for each type of invalid command that may be thrown during user input.
 */
public class TASyncException extends Exception {
    public TASyncException(String message) {
        super(message);
    }

    public static TASyncException invalidMarkCommand() {
        return new TASyncException("Invalid mark command, please specify which task to mark with an integer.");
    }

    public static TASyncException invalidUnmarkCommand() {
        return new TASyncException("Invalid unmark command, please specify which task to unmark with an integer.");
    }

    public static TASyncException invalidDeadlineCommand() {
        return new TASyncException(
                "Invalid deadline command, please specify when the deadline is in the future with \"/by\"."
        );
    }

    public static TASyncException invalidEventCommand() {
        return new TASyncException(
                "Invalid event command, please specify duration of event with \"/from\" and \"/to\"."
        );
    }
    public static TASyncException invalidTodoCommand() {
        return new TASyncException("Invalid Todo command, please name of Task to be done.");
    }
    public static TASyncException invalidConsultationCommand() {
        return new TASyncException(
                "Invalid Consultation command, specify duration of consultation with \"/from\" and \"/to\"."
        );
    }
    public static TASyncException invalidDeleteCommand() {
        return new TASyncException("Invalid delete command, please specify which task to delete with an integer.");
    }
    public static TASyncException invalidFindCommand() {
        return new TASyncException("Invalid Find command, please specify Keyword to Find tasks.");
    }
    public static TASyncException invalidRenameCommand() {
        return new TASyncException(
                "Invalid event command, please key command in the format: rename <task number> <new Name>."
        );
    }
    public static TASyncException invalidNewStudentCommand() {
        return new TASyncException("Invalid new student command, please key command in the format: " +
                        "NEWSTUDENT -t <name>,<dob>,<gender>,<contact>,<matricNumber>,<tutorialClass>"
        );
    }

    public static TASyncException invalidChangeRemarkCommand() {
        return new TASyncException("Invalid change remark command, please specify which task to change remark.");
    }

    public static TASyncException invalidDeleteStudentCommand() {
        return new TASyncException(
                "Invalid delete student command, please specify the tutorial number and " +
                        "the matriculation number of the student to delete from the list."
        );
    }

    public static TASyncException invalidFindStudentCommand() {
        return new TASyncException("Invalid find student command, please specify which student to find.");
    }

    public static TASyncException invalidCheckRemarkCommand() {
        return new TASyncException("Invalid check remark command, please specify which student to check.");
    }

    public static TASyncException invalidListUpcomingTutorialsCommand() {
        return new TASyncException("Invalid List Upcoming Tutorials command, please specify an end date");
    }

    public static TASyncException invalidListTutorialStudentsCommand() {
        return new TASyncException(
                "Invalid list all students in tutorial command, please key command in the format: " +
                        "LISTSTUDENTS -t <tutorialCode>"
        );
    }

    public static Exception invalidNewTutorialCommand() {
        return new TASyncException(
                "Invalid new tutorial command, please key command in the format:" +
                        " NEWTUTORIAL -t <tutorialCode>,<day_of_week>,<start_time>,<end_time>"
        );
    }

    public static Exception invalidDayOfWeek() {
        return new TASyncException("Invalid day of week command, please specify day of week from 1 to 7.");
    }


    public static Exception invalidDeleteTutorialCommand() {
        return new TASyncException(
                "Invalid delete tutorial command, please key command in the format:" +
                        "DELETE -t <tutorialCode>"
        );
    }

    public static TASyncException invalidListAttendanceListCommand() {
        return new TASyncException(
                "Invalid List all students in attendanceList command, " +
                        "please specify a valid attendancelist with a valid tutorial id and a valid week"
        );
    }

    public static TASyncException invalidmarkAttendanceListCommand() {
        return new TASyncException(
                "Invalid mark attendance command, " +
                        "please specify a valid attendancelist with a tutorial id, week and valid student id and name"
        );
    }

    public static TASyncException invalidNewMarksCommand(){
        return new TASyncException(
                "Invalid NewMarks command, please key command in the format: " +
                        "NewMarks -m <tutorial_id>,<matric_number>,<assignment_name>,<marks_achieved>,<maximum_marks>"
        );
    }

    public static TASyncException invalidListMarksCommand(){
        return new TASyncException(
                "Invalid list marks command, please key command in the format: " +
                        "list -m <tutorial_id>,<matric_number>"
        );
    }

    public static TASyncException invalidDeleteMarksCommand(){
        return new TASyncException(
                "Invalid delete marks command, please key command in the format: " +
                        "DeleteMarks -m <tutorial_id>,<matric_number>,<assignment_name>"
        );
    }

    public static TASyncException invalidTimeRange() {
        return new TASyncException("Invalid time range, start time must be before end time");
    }

    public static Exception overlappingTutorialTime() {
        return new TASyncException("The tutorial start time and end time is overlapping with another" +
                " tutorial time");
    }

    public static Exception duplicateTutorialName() {
        return new TASyncException("A tutorial with the same name already exists");
    }
}
