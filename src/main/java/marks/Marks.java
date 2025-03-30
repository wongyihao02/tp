package marks;

public class Marks {
    private String assignmentName;
    private int marks;
    private int maxMark;

    public Marks(String assignmentName, int marks, int maxMark) {
        this.assignmentName = assignmentName;
        this.marks = marks;
        this.maxMark = maxMark;
    }

    @Override
    public String toString(){
        return "Assignment: " + assignmentName + ". Marks achieved: "+ marks + "/" + maxMark;
    }

    public String toFileFormat(){
        return assignmentName + "," + marks + "," + maxMark;
    }

    public String getAssignmentName() {
        return assignmentName;
    }

    public void setAssignmentName(String assignmentName) {
        this.assignmentName = assignmentName;
    }

    public int getMarks() {
        return marks;
    }

    public void setMarks(int marks) {
        this.marks = marks;
    }

    public int getMaxMark() {
        return maxMark;
    }

    public void setMaxMark(int maxMark) {
        this.maxMark = maxMark;
    }
}
