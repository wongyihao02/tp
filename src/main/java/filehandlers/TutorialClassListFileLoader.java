package filehandlers;

import tutorial.TutorialClass;
import tutorial.TutorialClassList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TutorialClassListFileLoader implements FileLoader<TutorialClassList> {
    private final TutorialClassFileLoader tutorialLoader = new TutorialClassFileLoader();

    @Override
    public TutorialClassList loadFromFile(String filePath) {
        ArrayList<TutorialClass> tutorials = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            List<String> currentBlock = new ArrayList<>();

            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.startsWith("#")) {
                    if (!currentBlock.isEmpty()) {
                        // Load previous tutorial block
                        TutorialClass tutorial = tutorialLoader.loadFromBlock(currentBlock);
                        tutorials.add(tutorial);
                        currentBlock.clear();
                    }
                }
                if (!line.isEmpty()) {
                    currentBlock.add(line);
                }
            }

            // Final block
            if (!currentBlock.isEmpty()) {
                TutorialClass tutorial = tutorialLoader.loadFromBlock(currentBlock);
                tutorials.add(tutorial);
            }

        } catch (IOException e) {
            System.out.println("Error loading TutorialClassList: " + e.getMessage());
        }

        return new TutorialClassList(tutorials);
    }
}
