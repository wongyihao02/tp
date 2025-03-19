package tutorialCommands;

import Tutorial.TutorialClass;
import Tutorial.TutorialClassList;
import Util.DateTimeFormatterUtil;
import exception.TASyncException;
import taskCommands.Command;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class ListUpcomingTutorialsCommand implements Command<TutorialClassList> {

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

            // Calculate the number of days to add in order to reach the first tutorial session
            int daysUntilFirstTutorial = ((tutorialClasses.get(0).getDayOfWeek().getValue() - currentDayOfWeek.getValue()) < 0)
                    ? (tutorialClasses.get(0).getDayOfWeek().getValue() - currentDayOfWeek.getValue() + 7)
                    : (tutorialClasses.get(0).getDayOfWeek().getValue() - currentDayOfWeek.getValue());

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

        } catch (TASyncException e) {
            System.out.println(e.getMessage());
        }
    }
}
