package task;
/**
 * Enum representing the different types of tasks: Todo, Deadline, Event and Consultation.
 */
public enum TaskType {
    TODO("-pt"),
    DEADLINE("-pd"),
    EVENT("-pe"),
    CONSULTATION("-c");

    private final String commandShortcut;

    TaskType(String commandShortcut) {
        this.commandShortcut = commandShortcut;
    }

    public String getShortcut() {
        return commandShortcut;
    }

    /**
     * Converts a command shortcut into a TaskType.
     *
     * @param shortcut The command shortcut
     * @return The corresponding TaskType, or null if not found.
     */
    public static TaskType fromShortcut(String shortcut) {
        for (TaskType type : TaskType.values()) {
            if (type.getShortcut().equals(shortcut)) {
                return type;
            }
        }
        return null;
    }
}
