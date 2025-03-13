package Tutorial;

import java.util.ArrayList;

public class TutorialClassList {
    private ArrayList<TutorialClass> tutorialClasses;

    public TutorialClassList() {
        this.tutorialClasses = new ArrayList<>();
    }

    public TutorialClassList(ArrayList<TutorialClass> tutorialClasses) {
        this.tutorialClasses = tutorialClasses;
    }

    public ArrayList<TutorialClass> getTutorialClasses() {
        return tutorialClasses;
    }

    public void addTutorialClass(TutorialClass tutorialClass) {
        tutorialClasses.add(tutorialClass);
    }

    public TutorialClass getByName(String name) {
        for (TutorialClass t : tutorialClasses) {
            if (t.getTutorialName().equalsIgnoreCase(name)) {
                return t;
            }
        }
        return null;
    }
}