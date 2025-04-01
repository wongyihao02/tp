package task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;


class TaskListTest {
    private TaskList taskList;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        // Redirect System.out to capture console output
        System.setOut(new PrintStream(outContent));

        // Create a new task list with empty ArrayList to avoid file operations
        taskList = new TaskList(new ArrayList<>());
    }

    @Test
    void addTodoTask() {
        // Create and add a Todo task
        Todo todo = new Todo("Study Java", false);
        taskList.addTask(todo);

        // Verify task was added
        assertEquals(1, taskList.getTaskCount());
        assertEquals("Study Java", taskList.getTasks().get(0).getTaskName());
    }

    @Test
    void markTaskAsDone() {
        // Add a task
        Todo todo = new Todo("Complete assignment", false);
        taskList.addTask(todo);
        outContent.reset();

        // Mark task as done
        taskList.markTaskAsDone(1);

        // Verify task was marked
        assertTrue(taskList.getTasks().get(0).getIsDone());
        assertTrue(outContent.toString().contains("Complete assignment is marked"));
    }

    @Test
    void markTaskAsUndone() {
        // Add a task that is already done
        Todo todo = new Todo("Read chapter 5", true);
        taskList.addTask(todo);
        outContent.reset();

        // Mark task as undone
        taskList.markTaskAsUndone(1);

        // Verify task was unmarked
        assertFalse(taskList.getTasks().get(0).getIsDone());
        assertTrue(outContent.toString().contains("Read chapter 5 is unmarked"));
    }

    @Test
    void deleteTask() {
        // Add two tasks
        taskList.addTask(new Todo("Task 1", false));
        taskList.addTask(new Todo("Task 2", false));
        outContent.reset();

        // Delete first task
        taskList.deleteTask(1);

        // Verify first task was deleted
        assertEquals(1, taskList.getTaskCount());
        assertEquals("Task 2", taskList.getTasks().get(0).getTaskName());
    }

    @Test
    void renameTask() {
        // Add a task
        taskList.addTask(new Todo("Old name", false));
        outContent.reset();

        // Rename task
        taskList.renameTask(1, "New name");

        // Verify task was renamed
        assertEquals("New name", taskList.getTasks().get(0).getTaskName());
        assertTrue(outContent.toString().contains("Old name renamed to New name"));
    }

    @Test
    void getTaskListWithKeyWord() {
        // Add tasks with varying names
        taskList.addTask(new Todo("Java Programming", false));
        taskList.addTask(new Todo("Python Basics", false));
        taskList.addTask(new Todo("Advanced Java", false));

        // Filter tasks with keyword "Java"
        TaskList filteredList = taskList.getTaskListWithKeyWord("Java");

        // Verify only Java tasks were returned
        assertEquals(2, filteredList.getTaskCount());
        assertTrue(filteredList.getTasks().get(0).getTaskName().contains("Java"));
        assertTrue(filteredList.getTasks().get(1).getTaskName().contains("Java"));
    }

    @Test
    void invalidTaskOperations() {
        // Add one task
        taskList.addTask(new Todo("Single task", false));
        outContent.reset();

        // Try to mark non-existent task as done
        taskList.markTaskAsDone(2);
        assertTrue(outContent.toString().contains("no such task"));

        outContent.reset();
        // Try to rename non-existent task
        taskList.renameTask(2, "New name");
        assertTrue(outContent.toString().contains("no such task"));

        outContent.reset();
        // Try to delete non-existent task
        taskList.deleteTask(2);
        assertTrue(outContent.toString().contains("no such task"));
    }
}
