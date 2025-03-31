package marks;

import java.util.ArrayList;

/**
 * Represents a list of marks for a student.
 * Contains methods to add, delete, find and list marks.
 */
public class MarksList {
    private ArrayList<Marks> marksList;

    public MarksList() {
        marksList = new ArrayList<Marks>();
    }

    public MarksList(ArrayList<Marks> marksList) {
        this.marksList = marksList;
    }

    public ArrayList<Marks> getMarksList() {
        return marksList;
    }

    public void setMarksList(ArrayList<Marks> marksList) {
        this.marksList = marksList;
    }

    public void addMarks(Marks marks){
        marksList.add(marks);
    }

    public void deleteMarks(Marks marks){
        marksList.remove(marks);
    }

    /**
     * Prints all marks in the marks list along with the total average percentage.
     * If no marks have been added, it prints a message indicating that.
     */
    public void printMarks(){
        int totalMarks = 0;
        int totalMax = 0;
        if (marksList.isEmpty()) {
            System.out.println("No marks added yet.");
            return;
        }
        for (Marks marks : marksList){
            System.out.println(marks.toString());
            totalMarks += marks.getMarks();
            totalMax += marks.getMaxMark();
        }

        if (totalMax != 0){
            float avgPercent = ((float)totalMarks/(float)totalMax)*100;
            System.out.println("Average: " + String.format("%.1f", avgPercent) + "%");
        }
    }

    /**
     * Retrieves a Marks object based on the assignment name.
     *
     * @param assignmentName The name of the assignment to search for.
     * @return The Marks object if found, otherwise null.
     */
    public Marks getByAssignmentName(String assignmentName){
        for (Marks marks: marksList){
            if (marks.getAssignmentName().equalsIgnoreCase(assignmentName)){
                return marks;
            }
        }

        return null;
    }
}
