package taskCommands;

/**
 * An enumeration that contains all valid commands for the application.
 * Each command in this enum implements a method to print its usage and description.
 */
public enum CommandList {
    MARK {
        @Override
        public void printCommand() {
            System.out.println("MARK: Marks a task as done. Usage: MARK <task_number>");
        }
    }, UNMARK {
        @Override
        public void printCommand() {
            System.out.println("UNMARK: Marks a task as undone. Usage: UNMARK <task_number>");
        }
    }, TODO {
        @Override
        public void printCommand() {
            System.out.println("TODO: Creates a new todo task. Usage: TODO <task_description>");
        }
    }, EVENT {
        @Override
        public void printCommand() {
            System.out.println("EVENT: Creates a task with an event. Usage: EVENT <task_description> /from <start_time> /to <end_time>");
        }
    }, LIST {
        @Override
        public void printCommand() {
            System.out.println("LIST: Displays all tasks.");
        }
    }, BYE {
        @Override
        public void printCommand() {
            System.out.println("BYE: Exits the application.");
        }
    }, DELETE {
        @Override
        public void printCommand() {
            System.out.println("DELETE: Deletes a task. Usage: DELETE <task_number>");
        }
    }, FIND {
        @Override
        public void printCommand() {
            System.out.println("FIND: Finds a task based on the keyword given. Usage: FIND <keyword>");
        }
    }, RENAME {
        @Override
        public void printCommand() {
            System.out.println("RENAME: Renames a task based on the task number give . Usage: RENAME <task_number>");
        }
    }, NEW_STUDENT {
        @Override
        public void printCommand() {
            System.out.println("NEWSTUDENT: Adds a new student to the student list using the provided information. Usage: /NEWSTUDENT -s <name>,<dob>,<gender>,<contact>,<matric_number>,<tutorial_class>");
        }
    }, CHANGE_REMARK {
        @Override
        public void printCommand() {
            System.out.println("CHANGEREMARK: Updates the remark of a student. Usage: CHANGEREMARK <matric_number> <new_remark>");
        }
    },  CHECK_REMARK {
        public void printCommand() {
            System.out.println("CHECKREMARK: Checks remarks given to a student. Usage: CHECKREMARK <matric_number>");
        }
    }, DELETE_STUDENT {
        @Override
        public void printCommand() {
            System.out.println("DELETESTUDENT: Deletes a student from the student list. Usage: DELETESTUDENT <matric_number>");
        }
    }, FIND_STUDENT {
        @Override
        public void printCommand() {
            System.out.println("FINDSTUDENT: Finds a student based on the keyword (name or matric number). Usage: /FINDSTUDENT <keyword>");
        }
    }, NEW_TUTORIAL {
        public void printCommand() {
            System.out.println("NEWTUTORIAL: Creates a new tutorial. Usage: /NEWTUTORIAL <tutorial_name> <day_of_week> <start_time> <end_time>");
        }
    }, DELETE_TUTORIAL {
        public void printCommand() {
            System.out.println("DELETE_TUTORIAL: Deletes a specified tutorial. Usage: /DELETE <tutorial_name>");
        }
    };

    public abstract void printCommand();
}