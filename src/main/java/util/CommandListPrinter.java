package util;

import command.taskcommands.CommandList;

/**
 * Utility class for printing all available commands.
 * Commands are grouped by their categories and formatted neatly.
 */
public class CommandListPrinter {

    /**
     * Prints all available commands, grouped by category.
     */
    public static void printCommands() {
        System.out.println("Valid Commands:");
        System.out.println("=".repeat(50));

        printCommandGroup("Task Management", CommandList.MARK, CommandList.UNMARK, CommandList.TODO,
                CommandList.DEADLINE, CommandList.EVENT, CommandList.CONSULTATION, CommandList.RENAME,
                CommandList.LIST, CommandList.FIND, CommandList.DELETE);
        printCommandGroup("Student Management", CommandList.NEW_STUDENT, CommandList.DELETE_STUDENT,
                CommandList.FIND_STUDENT);
        printCommandGroup("Marks Management", CommandList.ADD_MARKS, CommandList.DELETE_MARKS,
                CommandList.LIST_MARKS);
        printCommandGroup("Tutorial Management", CommandList.NEW_TUTORIAL, CommandList.DELETE_TUTORIAL,
                CommandList.LISTTUTORIALS, CommandList.LISTTUTORIALSTUDENTS);
        printCommandGroup("Attendance Management", CommandList.CREATE,CommandList.MARKSTUDENT,
                CommandList.UNMARKSTUDENT, CommandList.LISTATTENDANCESTUDENTS);
        printCommandGroup("Remarks & Comments", CommandList.CHANGE_REMARK, CommandList.CHECK_REMARK,
                CommandList.COMMENT, CommandList.VIEWCOMMENT, CommandList.DELETECOMMENT);
        printCommandGroup("General Commands",
                CommandList.BYE);

        System.out.println("=".repeat(50));
    }

    /**
     * Prints a group of commands under a section header.
     *
     * @param groupName The name of the command group.
     * @param commands  The commands belonging to this group.
     */
    private static void printCommandGroup(String groupName, CommandList... commands) {
        System.out.println(groupName + ":");
        for (CommandList command : commands) {
            printFormattedCommand(command);
        }
        System.out.println();  // Blank line for separation
    }

    /**
     * Prints a single command in the desired format.
     *
     * @param command The command to be printed.
     */
    private static void printFormattedCommand(CommandList command) {
        System.out.printf("%-15s : %s%n", command.name(), getCommandUsage(command));
    }

    /**
     * Extracts the usage description for a command.
     *
     * @param command The command to describe.
     * @return A formatted usage description.
     */
    private static String getCommandUsage(CommandList command) {
        return switch (command) {
        case MARK ->
                "Marks a task as done\n  Usage: MARK -p <task_number>";
        case UNMARK ->
                "Marks a task as undone\n  Usage: UNMARK -p <task_number>";
        case TODO ->
                "Creates a new todo task\n  Usage: ADD -pt <task_description>";
        case DEADLINE ->
                "Creates a new deadline task\n  Usage: ADD -pd <task_description> /by <due_time>";
        case EVENT ->
                "Creates a new event\n  Usage: ADD -pe <task_description> /from <start_time> /to <end_time>";
        case CONSULTATION ->
                "Creates a new consultation with student\n " +
                        "Usage: ADD -c <student_name> /from <start_time> /to <end_time>";
        case NEW_STUDENT ->
                "Adds a new student\n  " +
                        "Usage: NEWSTUDENT -t <name>,<dob>,<gender>,<contact>,<matric_number>,<tutorial_class>";
        case DELETE_STUDENT ->
                "Removes a student from a tutorial\n  Usage: DELETESTUDENT -t <tutorial_name>,<matric_number>";
        case FIND_STUDENT ->
                "Finds a student by name or matric number\n  Usage: FIND -t <keyword>";
        case ADD_MARKS ->
                "Adds new marks for a student\n  " +
                        "Usage: NEWMARKS -m <tut_id>,<matric_num>,<assignment_name>,<marks_achieved> <max_marks>";
        case DELETE_MARKS ->
                "Deletes marks for a student\n  Usage: DELETEMARKS -m <tutorial_id>,<matric_number>,<assignment_name>";
        case LIST_MARKS ->
                "Lists marks for a student\n  Usage: LIST -m <tutorial_id>,<matric_number>";
        case NEW_TUTORIAL ->
                "Creates a new tutorial\n  Usage: NEWTUTORIAL -t <tutorial_name>,<day_of_week>,<start_time> <end_time>";
        case DELETE_TUTORIAL ->
                "Deletes a tutorial\n  Usage: DELETE -t <tutorial_name>";
        case LISTTUTORIALS ->
                "Lists tutorial classes up to a specified date\n  Usage: LIST -t <date>";
        case LISTTUTORIALSTUDENTS ->
                "Shows students in a tutorial\n  Usage: LISTSTUDENTS -t <tutorial_name>";
        case MARKSTUDENT ->
                "Marks a student's attendance as present\n " +
                        " Usage: MARK -a <Tutname>,<weeknum>,<studentname>,<studentmatricnumber>";
        case UNMARKSTUDENT ->
                "Marks a student's attendance as absent\n  " +
                        "Usage: UNMARK -a <Tutname>,<weeknum>,<studentname>,<studentmatricnumber>";
        case LISTATTENDANCESTUDENTS ->
                "Shows attendance records\n  Usage: LIST -a <Tutname>,<weeknum>";
        case CHANGE_REMARK ->
                "Updates a student's remark\n  Usage: CHANGEREMARK -t <tutorial_name>,<matric_number>,<new_remark>";
        case CHECK_REMARK ->
                "Checks remarks for a student\n  Usage: CHECKREMARK -t <tutorial_name>,<matric_number>";
        case COMMENT ->
                "Adds a comment to a student\n  " +
                        "Usage: COMMENT -a <Tutname>,<weeknum>,<studentname>,<studentmatricnumber>//comment1;comment2>";
        case VIEWCOMMENT ->
                "Views comments on a student\n  " +
                        "Usage: VIEWCOMMENT -a <Tutname>,<weeknum>,<studentname>,<studentmatricnumber>";
        case DELETECOMMENT ->
                "Deletes a student's comment\n  " +
                        "Usage: DELETECOMMENT -a <Tutname>,<weeknum>,<studentname>,<studentmatricnumber>//commentnum>";
        case LIST ->
                "Displays all tasks\n  Usage: LIST -p";
        case FIND ->
                "Finds a task based on a keyword\n  Usage: FIND -p <keyword>";
        case DELETE ->
                "Deletes a task\n  Usage: DELETE -p <task_number>";
        case BYE ->
                "Exits the application\n  Usage: BYE";
        case RENAME ->
                "Renames a task based on the task number given\n " +
                        "Usage: rename -p <task_number> <new_name>";

        case CREATE ->
                "Creates an attendance list for the given week if valid and not existing\n" +
                        "Usage: CREATE -ay <Tutname,weeknum>";
        default ->
                "Unknown command";
        };
    }
}
