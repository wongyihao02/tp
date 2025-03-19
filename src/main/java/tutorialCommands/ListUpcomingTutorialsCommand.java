package tutorialCommands;

import Tutorial.TutorialClass;
import Tutorial.TutorialClassList;
import Util.DateTimeFormatterUtil;
import exception.TASyncException;
import students.StudentList;
import taskCommands.Command;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class ListUpcomingTutorialsCommand implements Command<TutorialClassList> {

    @Override
    public void execute(String parts, TutorialClassList tutorial) {
        try {
            if (parts == null || parts.trim().isEmpty()) {
                throw TASyncException.invalidListUpcomingTutorialsCommand();
            }


            LocalDate dateEnd = DateTimeFormatterUtil.parseDate(parts);
            LocalDate today = LocalDate.now();
            DayOfWeek todayDay = today.getDayOfWeek();

            ArrayList<TutorialClass> tutorials = tutorial.getTutorialClasses();

            int increase = ((tutorials.get(0).getDayOfWeek().getValue() - todayDay.getValue()) < 0) ? (tutorials.get(0).getDayOfWeek().getValue() - todayDay.getValue() + 7) : (tutorials.get(0).getDayOfWeek().getValue() - todayDay.getValue());

            LocalDate firstTutorial = today.plusDays(increase);
            DayOfWeek firstTutorialDay = firstTutorial.getDayOfWeek();

            while (firstTutorial.isBefore(dateEnd)) {
                for (int i = 0; i < tutorials.size(); i++) {
                    TutorialClass tutorialClass = tutorials.get(i);
                    int daysDiff = tutorialClass.getDayOfWeek().getValue() - firstTutorialDay.getValue();
                    if (daysDiff <= ChronoUnit.DAYS.between(firstTutorial, dateEnd)) {
                        System.out.println(tutorialClass.getTutorialName() + " " + firstTutorial.plusDays(daysDiff) + " " + tutorialClass.getStartTime() + " " + tutorialClass.getEndTime());
                    }
                }

                firstTutorial = firstTutorial.plusDays(7);
                System.out.println();
            }
            System.out.println("End of list");



        } catch (TASyncException e) {
            System.out.println(e.getMessage());

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
