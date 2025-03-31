package command.commandhandler;


import command.attendancelistcommands.CommentOnStudentCommand;
import command.attendancelistcommands.CreateNewAttendanceList;
import command.attendancelistcommands.DeleteStudentComment;
import command.attendancelistcommands.MarkStudentAttendanceCommand;
import command.attendancelistcommands.ShowAttendanceListCommand;
import command.attendancelistcommands.UnmarkStudentAttendanceCommand;
import command.attendancelistcommands.ViewStudentCommentsCommand;
import command.markscommands.AddMarksCommand;
import command.markscommands.DeleteMarksCommand;
import command.markscommands.ListMarksCommand;
import command.studentcommands.ChangeRemarkCommand;
import command.studentcommands.CheckRemarkCommand;
import command.studentcommands.DeleteStudentCommand;
import command.studentcommands.FindStudentCommand;
import command.studentcommands.NewStudentCommand;
import command.taskcommands.ByeCommand;
import command.taskcommands.Command;
import command.taskcommands.ConsultationCommand;
import command.taskcommands.DeadlineCommand;
import command.taskcommands.DeleteTaskCommand;
import command.taskcommands.EventCommand;
import command.taskcommands.FindTaskCommand;
import command.taskcommands.ListTaskCommand;
import command.taskcommands.MarkTaskCommand;
import command.taskcommands.RenameTaskCommand;
import command.taskcommands.TodoCommand;
import command.taskcommands.UnmarkTaskCommand;
import command.tutorialcommands.DeleteTutorialCommand;
import command.tutorialcommands.ListTutorialStudentsCommand;
import command.tutorialcommands.ListUpcomingTutorialsCommand;
import command.tutorialcommands.NewTutorialCommand;
import task.TaskType;
import util.CommandListPrinter;

/**
 * The CommandFactory class is responsible for creating the appropriate Command object based on the
 * command string passed to it. This class is used to instantiate the correct command type for a given user input.
 * <p>
 * Example:
 * If the input is "TODO", the factory will create a TodoCommand.
 * <p>
 * The factory method ensures that the correct command is created, so that it can be executed in the CommandHandler.
 */
public class CommandFactory {

    /**
     * Creates the appropriate Command object based on the provided command string.
     * This method matches the command string with a predefined set of valid commands
     * and returns the corresponding Command object.
     *
     * @param commandString The command string to match and create the corresponding Command object.
     * @return A Command object corresponding to the provided command string.
     *      Returns null if the command is invalid.
     */
    public static Command createCommand(String commandString) {
        String[] parts = commandString.split("\\s+", 3); // Split into command, type, and rest of input
        if (parts.length < 2) {
            System.out.println("Invalid command format. Please use: /add -[type] [task details]");
            return null;
        }

        // Remove the leading '/' and convert the command to uppercase
        String command = parts[0].toUpperCase();
        String taskTypeShortcut = parts[1];
        String listType = parts[1];

        if ("HELP".equals(command)) {
            CommandListPrinter.printCommands();
            return null;
        }

        if ("ADD".equals(command)) {
            TaskType taskType = TaskType.fromShortcut(taskTypeShortcut);
            if (taskType == null) {
                System.out.println("Invalid task type. Use -c (Consultation), -pt (Todo), -pe (Event), -pd (Deadline)");
                return null;
            }

            switch (taskType) {
            case TODO:
                return new TodoCommand();
            case EVENT:
                return new EventCommand();
            case DEADLINE:
                return new DeadlineCommand();
            case CONSULTATION:
                return new ConsultationCommand();
            default:
                System.out.println("Unknown task type: " + taskType);
                return null;
            }
        }


        // Handle TaskList commands
        if (listType.equalsIgnoreCase("-p")) {
            switch (command) {
            case "DELETE":
                return new DeleteTaskCommand();
            case "MARK":
                return new MarkTaskCommand();
            case "UNMARK":
                return new UnmarkTaskCommand();
            case "LIST":
                return new ListTaskCommand();
            case "FIND":
                return new FindTaskCommand();
            case "RENAME":
                return new RenameTaskCommand();
            default:
                System.out.println("Sorry, TASync does not know what \"" + command + "\" means.");
                CommandListPrinter.printCommands();
                return null;
            }
        } else if (listType.equalsIgnoreCase("-t")) {
            switch (command) {
            case "NEWTUTORIAL":
                return new NewTutorialCommand();
            case "DELETE":
                return new DeleteTutorialCommand();
            case "LIST":
                return new ListUpcomingTutorialsCommand();
            case "NEWSTUDENT":
                return new NewStudentCommand();
            case "DELETESTUDENT":
                return new DeleteStudentCommand();
            case "LISTSTUDENTS":
                return new ListTutorialStudentsCommand();
            case "FIND":
                return new FindStudentCommand();
            case "CHANGEREMARK":
                return new ChangeRemarkCommand();
            case "CHECKREMARK":
                return new CheckRemarkCommand();
            default:
                System.out.println("Sorry, TASync does not know what \"" + command + "\" means.");
                CommandListPrinter.printCommands();
                return null;
            }
        }  else if (listType.equalsIgnoreCase("-a")) {
            switch (command) {
            case "MARK":
                return new MarkStudentAttendanceCommand();
            case "UNMARK":
                return new UnmarkStudentAttendanceCommand();
            case "COMMENT":
                return new CommentOnStudentCommand();
            case "VIEWCOMMENT":
                return new ViewStudentCommentsCommand();
            case "DELETECOMMENT":
                return new DeleteStudentComment();
            case "LIST":
                return new ShowAttendanceListCommand();
            default:
                System.out.println("Sorry, TASync does not know what \"" + command + "\" means.");
                CommandListPrinter.printCommands();
                return null;
            }
        } else if (listType.equalsIgnoreCase("-at")) {
            if (command.equals("CREATE")) {
                return new CreateNewAttendanceList();
            }
            System.out.println("Sorry, TASync does not know what \"" + command + "\" means.");
            CommandListPrinter.printCommands();
            return null;
        } else if (listType.equalsIgnoreCase("-m")){
            switch (command){
            case "NEWMARKS":
                return new AddMarksCommand();
            case "DELETEMARKS":
                return new DeleteMarksCommand();
            case "LIST":
                return new ListMarksCommand();
            default:
                System.out.println("Sorry, TASync does not know what \"" + command + "\" means.");
                CommandListPrinter.printCommands();
                return null;
            }
        }

        if (command.equals("BYE")) {
            return new ByeCommand();
        }

        return null;
    }
}
