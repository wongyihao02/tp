package util;

import task.Consultation;
import task.Deadline;
import task.Event;
import task.Task;
import task.TaskList;
import task.Todo;
import tutorial.TutorialClass;
import tutorial.TutorialClassList;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Manages the daily schedule by printing tasks and tutorial classes occurring today.
 */
public class DailySchedule {
    private final TaskList taskList;
    private final TutorialClassList tutorialClassList;

    public DailySchedule(TaskList taskList, TutorialClassList tutorialClassList) {
        this.taskList = taskList;
        this.tutorialClassList = tutorialClassList;
    }

    /**
     * Prints the schedule for today including todos, deadlines, events, consultations, and tutorial classes.
     */
    public void displayScheduleForToday() {
        LocalDate today = LocalDate.now();

        System.out.println("Schedule for " + today + ":\n");

        System.out.println("Todos:");
        ArrayList<Task> tasks = taskList.getTasks();
        boolean hasTodos = false;
        for (Task task : tasks) {
            if (task instanceof Todo) {
                System.out.println(" - " + task.getTaskName());
                hasTodos = true;
            }
        }
        if (!hasTodos) {
            System.out.println("No pending todos.");
        }

        System.out.println();

        System.out.println("Deadlines Due Today:");
        boolean hasDeadlines = false;
        for (Task task : tasks) {
            if (task instanceof Deadline && ((Deadline) task).isDueToday(today)) {
                System.out.println(" - " + task.getTaskName() + " (by: " + ((Deadline) task).getDeadline() + ")");
                hasDeadlines = true;
            }
        }
        if (!hasDeadlines) {
            System.out.println("No deadlines due today.");
        }

        System.out.println();

        System.out.println("Events Starting Today:");
        boolean hasEvents = false;
        for (Task task : tasks) {
            if (task instanceof Event && ((Event) task).isStartingToday(today)) {
                System.out.println(" - " + task.getTaskName() + " (from: " + ((Event) task).getEventStart() + ")");
                hasEvents = true;
            }
        }
        if (!hasEvents) {
            System.out.println("No events starting today.");
        }

        System.out.println();

        System.out.println("Consultations Starting Today:");
        boolean hasConsultations = false;
        for (Task task : tasks) {
            if (task instanceof Consultation && ((Consultation) task).isStartingToday(today)) {
                System.out.println(" - " + task.getTaskName() +
                        " (from: " + ((Consultation) task).getConsultationStart() + ")");
                hasConsultations = true;
            }
        }
        if (!hasConsultations) {
            System.out.println("No consultations scheduled for today.");
        }

        System.out.println();

        System.out.println("Tutorial Classes for Today:");
        boolean hasClasses = false;
        ArrayList<TutorialClass> tutorials = tutorialClassList.getTutorialClasses();
        for (TutorialClass tutorial : tutorials) {
            if (tutorial.isHappeningToday(today)) {
                String tutorialInfo = " - " + "Tutorial Name: " + tutorial.getTutorialName() +
                        ", Day of Week: " + tutorial.getDayOfWeek() +
                        ", From: " + tutorial.getStartTime() +
                        ", To: " + tutorial.getEndTime();
                System.out.println(tutorialInfo);
                hasClasses = true;
            }
        }
        if (!hasClasses) {
            System.out.println("No tutorial classes today.");
        }
    }
}
