package filehandlers;

import students.Student;
import tutorial.TutorialClass;
import tutorial.TutorialClassList;
import util.DateTimeFormatterUtil;

import java.io.FileWriter;
import java.io.IOException;

public class TutorialClassListFileSaver implements FileSaver<TutorialClassList> {
    @Override
    public void saveToFile(TutorialClassList tutorialList, String directoryPath) {
        String filePath = directoryPath + "/AllTutorials.csv";

        try (FileWriter writer = new FileWriter(filePath)) {
            for (TutorialClass tutorial : tutorialList.getTutorialClasses()) {
                // Write tutorial block header
                writer.write("# " + tutorial.getTutorialName() + "," +
                        tutorial.getDayOfWeek().toString() + "," +
                        tutorial.getStartTime().toString() + "," +
                        tutorial.getEndTime().toString() + "\n");

                // Write each student in this tutorial
                for (Student student : tutorial.getStudentList().getStudents()) {
                    writer.write(student.getName() + "," +
                            DateTimeFormatterUtil.formatDate(student.getDateOfBirth()) + "," +
                            student.getGender() + "," +
                            student.getContact() + "," +
                            student.getMatricNumber() + "," +
                            (student.getRemark() != null ? student.getRemark() : "") + "\n");
                }

                writer.write("\n"); // Separate blocks
            }
        } catch (IOException e) {
            System.out.println("Error saving TutorialClassList: " + e.getMessage());
        }
    }
}
