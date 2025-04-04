package command.tutorialcommands;

import tutorial.TutorialClass;
import tutorial.TutorialClassList;
import util.DateTimeFormatterUtil;
import exception.TASyncException;
import command.taskcommands.Command;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

/**
 * Represents the "LIST_UPCOMING_TUTORIALS" command that lists upcoming tutorial classes
 * based on the current date and an input end date.
 * The tutorials are displayed along with their names, dates, and times until the specified end date.
 * If the input is invalid or any exceptions occur, appropriate error messages are displayed.
 */
public class ListUpcomingTutorialsCommand implements Command<TutorialClassList> {

    /**
     * Executes the "LIST_UPCOMING_TUTORIALS" command to display all upcoming tutorial sessions
     * from the current date until the given end date. The list includes the tutorial class name,
     * date, start time, and end time.
     *
     * @param inputDate The end date as a string, used to limit the range of upcoming tutorials.
     * @param tutorialClassList The list of tutorial classes to search through.
     */
    @Override
    public void execute(String inputDate, TutorialClassList tutorialClassList) {
        try {
            // Check if the input date string is empty or null
            if (inputDate == null || inputDate.trim().isEmpty()) {
                throw TASyncException.invalidListUpcomingTutorialsCommand();
            }

            // Parse the input date into LocalDate format
            LocalDate endDate = DateTimeFormatterUtil.parseDate(inputDate);
            LocalDate currentDate = LocalDate.now();
            DayOfWeek currentDayOfWeek = currentDate.getDayOfWeek();

            // Get the list of tutorials
            ArrayList<TutorialClass> tutorialClasses = tutorialClassList.getTutorialClasses();
            if(tutorialClasses.isEmpty()) {
                System.out.println("There are no tutorial created yet.");
                return;
            }

            // Calculate the number of days to add in order to reach the first tutorial session
            int daysUntilFirstTutorial = (tutorialClasses.get(0).getDayOfWeek().getValue()
                    - currentDayOfWeek.getValue()) < 0
                    ? (tutorialClasses.get(0).getDayOfWeek().getValue()
                    - currentDayOfWeek.getValue() + 7)
                    : (tutorialClasses.get(0).getDayOfWeek().getValue()
                    - currentDayOfWeek.getValue());

            LocalDate nextTutorialDate = currentDate.plusDays(daysUntilFirstTutorial);
            DayOfWeek nextTutorialDayOfWeek = nextTutorialDate.getDayOfWeek();

            // Loop through the upcoming tutorial sessions until the end date

            while (nextTutorialDate.isBefore(endDate)) {
                for (TutorialClass tutorialClass : tutorialClasses) {
                    int daysDifference = tutorialClass.getDayOfWeek().getValue() - nextTutorialDayOfWeek.getValue();
                    if (daysDifference <= ChronoUnit.DAYS.between(nextTutorialDate, endDate)) {
                        System.out.println(
                                tutorialClass.getTutorialName() + " " +
                                        nextTutorialDate.plusDays(daysDifference) + " " +
                                        tutorialClass.getStartTime() + " " +
                                        tutorialClass.getEndTime()
                        );
                    }
                }

                // Move to the next week's tutorial date
                nextTutorialDate = nextTutorialDate.plusDays(7);
                System.out.println();
            }
            System.out.println("End of list");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
