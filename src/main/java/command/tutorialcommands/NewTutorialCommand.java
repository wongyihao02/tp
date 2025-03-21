package command.tutorialcommands;

import tutorial.TutorialClass;
import tutorial.TutorialClassList;
import exception.TASyncException;
import students.StudentList;
import command.taskcommands.Command;

import java.time.DayOfWeek;
import java.time.LocalTime;

public class NewTutorialCommand implements Command<TutorialClassList> {

    @Override
    public void execute(String input, TutorialClassList tutorialClassList) {
        try {
            // Validate input string
            if (input == null || input.trim().isEmpty()) {
                throw TASyncException.invalidNewTutorialCommand();
            }

            // Split the input to get tutorial details
            String[] inputParts = input.split("\\s+");
            if (inputParts.length != 4) {
                throw TASyncException.invalidNewTutorialCommand();
            }

            String tutorialName = inputParts[0];
            String dayOfWeekStr = inputParts[1];
            String startTimeStr = inputParts[2];
            String endTimeStr = inputParts[3];

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

            LocalTime startTime = LocalTime.parse(startTimeStr);
            LocalTime endTime = LocalTime.parse(endTimeStr);

            // Check if the tutorial already exists in the list
            for (TutorialClass existingTutorial : tutorialClassList.getTutorialClasses()) {
                if (existingTutorial.getTutorialName().equals(tutorialName) &&
                        existingTutorial.getDayOfWeek().getValue() == dayOfWeek &&
                        existingTutorial.getStartTime().equals(startTime) &&
                        existingTutorial.getEndTime().equals(endTime)) {
                    throw TASyncException.duplicateTutorial();
                }
            }

            // Create a new TutorialClass object
            StudentList emptyStudentList = new StudentList(); // Empty list for students
            TutorialClass newTutorial = new TutorialClass();
            newTutorial.setTutorialName(tutorialName);
            newTutorial.setDayOfWeek(DayOfWeek.of(dayOfWeek));  // Convert int to DayOfWeek
            newTutorial.setStartTime(startTime);
            newTutorial.setEndTime(endTime);
            newTutorial.setStudentList(emptyStudentList);  // Set an empty student list

            // Add the new tutorial to the tutorial class list
            tutorialClassList.addTutorialClass(newTutorial);

            // Output success message
            System.out.println("New tutorial added: " + newTutorial);

        } catch (TASyncException e) {
            // Handle TASyncException
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            // Handle any other unexpected exceptions
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }
    }
}
