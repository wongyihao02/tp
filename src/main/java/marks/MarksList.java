package marks;

import java.util.ArrayList;

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
}
