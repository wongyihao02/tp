package tutorial;

import students.StudentList;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;

public class TutorialClass {
    private String tutorialName;
    private StudentList studentList=new StudentList();
    private LocalTime startTime;
    private LocalTime endTime;
    private DayOfWeek dayOfWeek;

    public TutorialClass(String tutorialName){
        this.tutorialName = tutorialName;
    }
    public TutorialClass(){
    }

    public String getTutorialName() {
        return tutorialName;
    }

    public void setTutorialName(String tutorialName) {
        this.tutorialName = tutorialName;
    }

    public StudentList getStudentList() {
        return studentList;
    }

    public void setStudentList(StudentList studentList) {
        this.studentList = studentList;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    /**
     * Checks if this tutorial class is happening today.
     *
     * @param today The current date.
     * @return true if the class is on the same day of the week as today, false otherwise.
     */
    public boolean isHappeningToday(LocalDate today) {
        return today.getDayOfWeek() == this.dayOfWeek;
    }

    @Override
    public String toString() {
        return "tutorialName: " + tutorialName +
                ", dayOfWeek: " + dayOfWeek +
                ", from: " + startTime +
                ", to: " + endTime +
                ", studentList: " + studentList;
    }
}
