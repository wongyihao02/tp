package filehandlers;

import marks.Marks;
import students.Student;
import tutorial.TutorialClass;
import tutorial.TutorialClassList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MarksListLoader implements FileLoader<TutorialClassList> {
    private static final String FILE_PATH = "./data/marks.txt";
    @Override
    public TutorialClassList loadFromFile(String filePath) {
        throw new UnsupportedOperationException("Use loadMarks(TutorialClassList, filePath) instead.");
    }

    public TutorialClassList loadMarks(TutorialClassList tutorialClassList){
        FileCreator.createFileIfNotExists(FILE_PATH, "./data");

        try(BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))){
            String line;
            while ((line = reader.readLine()) != null){
                if (line.trim().isEmpty()){
                    continue;
                }

                String[] parts = line.split(",");
                if (parts.length < 5){
                    System.out.println("Invalid marks found");
                    continue;
                }

                String tutID = parts[0];
                String matric = parts[1];
                String assignmentName = parts[2];
                int marks = Integer.parseInt(parts[3]);
                int maxMark = Integer.parseInt(parts[4]);

                TutorialClass tutorialClass = tutorialClassList.getByName(tutID);
                Student student = tutorialClass.getStudentList().getStudentByMatricNumber(matric);
                Marks newMarks = new Marks(assignmentName, marks, maxMark);
                student.getMarksList().addMarks(newMarks);
            }
        } catch (IOException e){
            System.out.println("Error loading marks" + e.getMessage());
        }

        return tutorialClassList;
    }
}
