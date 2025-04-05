package command.tutorialcommands;

import tutorial.TutorialClass;
import tutorial.TutorialClassList;
import exception.TASyncException;
import command.taskcommands.Command;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import static command.studentcommands.StudentCommandHelper.parseInput;


/**
 * Represents the "NEW_TUTORIAL" command that adds a new tutorial class to the tutorial class list.
 * The tutorial is added only if its details are valid and do not duplicate any existing tutorials.
 * If any of the inputs are invalid or a duplicate tutorial is found, appropriate exceptions are thrown.
 */
public class NewTutorialCommand implements Command<TutorialClassList> {

    /**
     * Executes the "NEW_TUTORIAL" command to add a new tutorial class to the tutorial class list.
     * The tutorial class must have a valid name, day of the week, start time, and end time.
     * Duplicate tutorials with the same name or overlapping start time and end time are not allowed.
     *
     * @param input             The input string containing the tutorial details (name, day, start time, and end time).
     * @param tutorialClassList The list of tutorial classes to which the new tutorial class will be added.
     */
    @Override
    public void execute(String input, TutorialClassList tutorialClassList) {
        try {

            //Validate that the necessary inputs are correctly inputted
            String[] inputParts = parseInput(input,4);

            String tutorialName = inputParts[0].trim();
            String dayOfWeekStr = inputParts[1].trim();
            String startTimeStr = inputParts[2].trim();
            String endTimeStr = inputParts[3].trim();

            if (tutorialName.isBlank() || dayOfWeekStr.isBlank() || startTimeStr.isBlank() || endTimeStr.isBlank()) {
                throw TASyncException.invalidNewTutorialCommand();
            }

            // Parse and validate day of the week
            int dayOfWeek;
            try {
                dayOfWeek = Integer.parseInt(dayOfWeekStr);
                if (dayOfWeek < 1 || dayOfWeek > 7) {
                    throw TASyncException.invalidDayOfWeek();
                }
            } catch (NumberFormatException e) {
                throw TASyncException.invalidDayOfWeek();
            }

            // Parse and validate start and end time
            LocalTime startTime;
            LocalTime endTime;

            try {
                startTime = LocalTime.parse(startTimeStr);
                endTime = LocalTime.parse(endTimeStr);
            } catch (DateTimeParseException e) {
                throw new TASyncException("Invalid time format. Please enter valid start and end times.");
            }

            if (!endTime.isAfter(startTime)) {
                throw TASyncException.invalidTimeRange();
            }


            for (TutorialClass existingTutorial : tutorialClassList.getTutorialClasses()) {
                // Check for duplicate tutorial name
                if (existingTutorial.getTutorialName().equalsIgnoreCase(tutorialName)) {
                    throw TASyncException.duplicateTutorialName();
                }

                // Check for overlapping time intervals on the same day
                if (existingTutorial.getDayOfWeek().getValue() == dayOfWeek) {
                    LocalTime existingStart = existingTutorial.getStartTime();
                    LocalTime existingEnd = existingTutorial.getEndTime();

                    boolean overlaps = !(endTime.isBefore(existingStart) || startTime.isAfter(existingEnd));
                    if (overlaps) {
                        throw TASyncException.overlappingTutorialTime();
                    }
                }
            }

            TutorialClass newTutorial = new TutorialClass(tutorialName, DayOfWeek.of(dayOfWeek), startTime, endTime);
            // Add the new tutorial to the tutorial class list
            tutorialClassList.addTutorialClass(newTutorial);

            System.out.printf(
                    "Tutorial \"%s\" successfully scheduled on %s from %s to %s.%n",
                    tutorialName,
                    DayOfWeek.of(dayOfWeek),
                    startTime,
                    endTime
            );

        } catch (TASyncException e) {
            // Handle TASyncException
            System.out.println(e.getMessage());
        } catch (Exception e) {
            // Handle any other unexpected exceptions
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }
    }
}
