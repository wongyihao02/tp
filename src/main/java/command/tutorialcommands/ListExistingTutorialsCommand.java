package command.tutorialcommands;

import tutorial.TutorialClass;
import tutorial.TutorialClassList;
import command.taskcommands.Command;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Represents the "LIST_EXISTING_TUTORIALS" command that lists all existing tutorial classes.
 * Displays each tutorial’s name, day of week, start time, and end time.
 * If there are no tutorials, a message is shown.
 */
public class ListExistingTutorialsCommand implements Command<TutorialClassList> {

    /**
     * Executes the "LIST_EXISTING_TUTORIALS" command to show all created tutorial sessions.
     *
     * @param input Unused in this command.
     * @param tutorialClassList The list of tutorial classes to display.
     */
    @Override
    public void execute(String input, TutorialClassList tutorialClassList) {
        ArrayList<TutorialClass> tutorialClasses = tutorialClassList.getTutorialClasses();

        if (tutorialClasses.isEmpty()) {
            System.out.println("There are no tutorials created yet.");
            return;
        }

        // Sort the tutorial classes by day of the week first, then by start time
        tutorialClasses.sort(Comparator
                .comparing(TutorialClass::getDayOfWeek)  // Sort by day of week
                .thenComparing(TutorialClass::getStartTime)  // Then by start time
        );

        System.out.println("Existing Tutorials:");
        for (TutorialClass tutorial : tutorialClasses) {
            System.out.println(
                    tutorial.getTutorialName() + " - " +
                            tutorial.getDayOfWeek() + " " +
                            tutorial.getStartTime() + " to " +
                            tutorial.getEndTime()
            );
        }
        System.out.println("End of list");
    }
}
