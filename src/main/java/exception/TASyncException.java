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
        return new TASyncException("Invalid deadline command, please specify when deadline is in the future. with \"/by\".");
    }

    public static TASyncException invalidEventCommand() {
        return new TASyncException("Invalid event command, please specify duration of event with \"/from\" and \"/to\".");
    }
    public static TASyncException invalidTodoCommand() {
        return new TASyncException("Invalid Todo command, please name of Task to be done.");
    }
    public static TASyncException invalidConsultationCommand() {
        return new TASyncException("Invalid Consultation command, specify duration of consultation with \"/from\" and \"/to\".");
    }
    public static TASyncException invalidDeleteCommand() {
        return new TASyncException("Invalid delete command, please specify which task to delete with an integer.");
    }
    public static TASyncException invalidFindCommand() {
        return new TASyncException("Invalid Find command, please specify Keyword to Find tasks.");
    }
    public static TASyncException invalidRenameCommand() {
        return new TASyncException("Invalid event command, please key command in the format: rename <task number> <new Name>.");
    }
    public static TASyncException invalidNewStudentCommand() {
        return new TASyncException("Invalid NewStudent command, please key command in the format: NewStudent <name> <age> <gender> <contact> <matricNumber> <tutorialClass>.");
    }

    public static TASyncException invalidChangeRemarkCommand() {
        return new TASyncException("Invalid change remark command, please specify which task to change remark.");
    }

    public static TASyncException invalidDeleteStudentCommand() {
        return new TASyncException("Invalid delete student command, please specify which student to delete from the list.");
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
}
