package task;

import java.util.ArrayList;
import java.util.Iterator;
/**
 * Represents a list of tasks.
 * This class provides methods for managing tasks (e.g., adding, marking, deleting, and searching tasks).
 */
public class TaskList {
    private ArrayList<Task> tasks;
    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }
    /**
     * Constructs a TaskList with a specified list of tasks.
     *
     * @param tasks The list of tasks to initialize the TaskList with.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }
    /**
     * Returns the list of tasks.
     *
     * @return The list of tasks in the TaskList.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Filters tasks based on a keyword and returns a new TaskList containing matching tasks.
     *
     * @param keyword The keyword to search for in task names.
     * @return A TaskList containing tasks that match the keyword.
     */
    public TaskList getTaskListWithKeyWord(String keyword) {
        TaskList tasksWithKeyWord = new TaskList(new ArrayList<>(tasks));

        Iterator<Task> iterator = tasksWithKeyWord.tasks.iterator();
        while (iterator.hasNext()) {
            Task task = iterator.next();
            if (!task.getTaskName().toLowerCase().contains(keyword.toLowerCase())) {
                iterator.remove();
            }
        }

        return tasksWithKeyWord;
    }
    /**
     * Adds a Todo task to the TaskList.
     *
     * @param todo The Todo task to be added.
     */
    public void addTask(Todo todo) {
        tasks.add(todo);
        System.out.println("task added:");
        todo.printTask();
        todo.printDue();
        System.out.println("Now you have " + tasks.toArray().length + " tasks in the list.");

    }

    /**
     * Adds a Deadline task to the TaskList.
     *
     * @param deadline The Deadline task to be added.
     */
    public void addTask(Deadline deadline) {
        tasks.add(deadline);
        System.out.println("task added:");
        deadline.printTask();
        deadline.printDue();
        System.out.println("Now you have " + tasks.toArray().length + " tasks in the list.");
    }
    /**
     * Adds an Event task to the TaskList.
     *
     * @param event The Event task to be added.
     */
    public void addTask(Event event) {
        tasks.add(event);
        System.out.println("task added:");
        event.printTask();
        event.printDue();
        System.out.println("Now you have " + tasks.toArray().length + " tasks in the list.");
    }
    /**
     * Adds a Consultation task to the TaskList.
     *
     * @param consultation The Consultation task to be added.
     */
    public void addTask(Consultation consultation) {
        tasks.add(consultation);
        System.out.println("Task added:");
        consultation.printTask();
        consultation.printDue();
        System.out.println("Now you have " + tasks.toArray().length + " tasks in the list.");
    }
    /**
     * Returns the total number of tasks in the TaskList.
     *
     * @return The number of tasks in the TaskList.
     */
    public int getTaskCount() {
        return tasks.toArray().length;
    }

    /**
     * Marks a task as completed based on its task ID.
     *
     * @param taskId The ID of the task to mark as done.
     */
    public void markTaskAsDone(int taskId) {
        if (taskId <= tasks.toArray().length && taskId>0) { //check if taskId is valid
            if (tasks.get(taskId - 1).getIsDone()) { //check if task is already unmarked
                System.out.println(tasks.get(taskId - 1).getTaskName() + " was already marked as done");
            } else {
                tasks.get(taskId - 1).setIsDone(true); //valid and not done, mark as done
                System.out.println(tasks.get(taskId - 1).getTaskName() + " is marked");
            }
        } else {
            System.out.println("no such task"); //not valid print error
        }
    }
    /**
     * Marks a task as undone based on its task ID.
     *
     * @param taskId The ID of the task to mark as undone.
     */
    public void markTaskAsUndone(int taskId) {
        if (taskId <= tasks.toArray().length && taskId>0) { //check if taskId is valid
            if (!tasks.get(taskId - 1).getIsDone()) { //check if task is already unmarked
                System.out.println(tasks.get(taskId - 1).getTaskName() + " was already unmarked");
            } else {
                tasks.get(taskId - 1).setIsDone(false); //if valid and marked, unmark it
                System.out.println(tasks.get(taskId - 1).getTaskName() + " is unmarked");
            }
        } else {
            System.out.println("no such task"); //not valid print error
        }
    }
    /**
     * Deletes a task based on its task ID.
     *
     * @param taskId The ID of the task to delete.
     */
    public void deleteTask(int taskId) {
        if (taskId <= tasks.toArray().length && taskId > 0) {
            Task task = tasks.get(taskId - 1);
            tasks.remove(taskId - 1);
            System.out.println("deleted task: ");
            task.printTask();
            System.out.println();
        } else {
            System.out.println("no such task");
        }
    }
    /**
     * Renames a task based on its task ID.
     *
     * @param taskId The ID of the task to rename.
     * @param newTaskName The new name for the task.
     */
    public void renameTask(int taskId, String newTaskName) {
        if (taskId <= tasks.toArray().length && taskId>0) { //check if taskId valid
            if (tasks.get(taskId - 1).getTaskName().equals(newTaskName)) { //check if name is same
                System.out.println("please use a new name"); //if no change in name then print error
            } else {
                System.out.println(tasks.get(taskId - 1).getTaskName() + " renamed to " + newTaskName);
                tasks.get(taskId - 1).setTaskName(newTaskName);
            }
        }else{
            System.out.println("no such task");
        }
    }
    /**
     * Prints the list of tasks in the TaskList.
     */
    public void printTaskList() {
        for (int i = 0; i < tasks.toArray().length; i++) {
            System.out.print(i + 1 + ".");
            tasks.get(i).printTaskType();
            tasks.get(i).printIsDone();
            System.out.print(" " + tasks.get(i).getTaskName());
            tasks.get(i).printDue();
        }
    }
}
